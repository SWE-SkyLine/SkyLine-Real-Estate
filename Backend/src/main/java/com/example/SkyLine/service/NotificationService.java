package com.example.SkyLine.service;

import com.example.SkyLine.DTO.NotificationRequestDTO;
import com.example.SkyLine.DTO.UserPromoteDTO;
import com.example.SkyLine.entity.Client;
import com.example.SkyLine.entity.Company;
import com.example.SkyLine.entity.Notification;
import com.example.SkyLine.entity.User;
import com.example.SkyLine.enums.UserRoleEnum;
import com.example.SkyLine.repository.ClientRepository;
import com.example.SkyLine.repository.CompanyRepository;
import com.example.SkyLine.repository.NotificationRepository;
import com.example.SkyLine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    private final ClientRepository clientRepository;
    @Autowired
    public NotificationService(NotificationRepository notificationRepository, UserRepository userRepository, CompanyRepository companyRepository, ClientRepository clientRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
        this.clientRepository = clientRepository;

    }
    public boolean notify(int requesterID, int candidateID){
        long previousRequests=notificationRepository.countCandidatePreviousRequests(candidateID);
        System.out.println("previousRequests= "+previousRequests);
        if(previousRequests>0){return false;}
        ArrayList<Integer> SuperAdminsIDs=userRepository.getSuperAdminsID("SUPERADMIN");
        System.out.println("SuperAdminsIDs= "+SuperAdminsIDs.size());
        for (Integer SuperAdminsID : SuperAdminsIDs
             ) {
            Notification request=new Notification(requesterID,candidateID,SuperAdminsID);
            notificationRepository.save(request);
        }

        return true;

        
    }

    public boolean request(int requesterId, int candidateId){
        UserRoleEnum role=userRepository.findUserById(requesterId).getUserRole();
        if (role==UserRoleEnum.ADMIN){
            System.out.println("here ");
           return notify(requesterId,candidateId);
        }
        else if(role==UserRoleEnum.COMPANY){
           Notification request=new Notification(requesterId,candidateId);
              notificationRepository.save(request);
              return true;
        }
        else {
           System.out.println("You are not allowed to request ");
           return false;
        }


    }
 public void approveRequest(int NotificationId) {
        Notification request=notificationRepository.findNotificationById(NotificationId);


        int responderId=request.getResponder_id();
     System.out.println("Responder id= "+responderId);
        UserRoleEnum role=userRepository.findUserById(responderId).getUserRole();
     System.out.println("user role = "+String.valueOf(role));
        if(role==UserRoleEnum.SUPERADMIN){
            int candidateId=request.getCandidate_id();
            int requesterId=request.getRequester_id();
            notificationRepository.deleteNotificationById(requesterId, candidateId, responderId);
            notificationRepository.updateNotificationById(NotificationId, true, new java.sql.Date(System.currentTimeMillis()));
            userRepository.promoteToAdmin(candidateId, UserRoleEnum.ADMIN);

        }
        else if(role==UserRoleEnum.CLIENT){
            int companyId=request.getRequester_id();
            System.out.println(companyId);
            System.out.println(responderId);
            notificationRepository.updateNotificationById(NotificationId, true, new java.sql.Date(System.currentTimeMillis()) );
            Company company=companyRepository.findCompanyById(companyId);
            Client client=clientRepository.findClientById(responderId);
            company.getClients().add(client);
            companyRepository.save(company);

        }

 }
    public void rejectRequest(int NotificationId){
        notificationRepository.updateNotificationById(NotificationId, false, new java.sql.Date(System.currentTimeMillis()) );
    }



    public ArrayList<NotificationRequestDTO> getUserNotifications(int userID){

        UserRoleEnum role=userRepository.findUserById(userID).getUserRole();
        if(role==UserRoleEnum.ADMIN){
            return getAdminNotifications(userID);
        }
        else if(role==UserRoleEnum.COMPANY){
            return getCompanyNotifications(userID);
        }
        else if(role==UserRoleEnum.CLIENT){
            return getClientNotifications(userID);
        }
        else{
            return getSuperAdminNotifications(userID);
        }

    }



    public ArrayList<NotificationRequestDTO> getAdminNotifications(int AdminID){

        ArrayList<Notification> answeredRequests= notificationRepository.getAdminAnsweredRequests(AdminID);
        ArrayList<Notification> pendingRequests= notificationRepository.getAdminPendingRequests(AdminID);
        ArrayList<NotificationRequestDTO> requests=new ArrayList<>();
        for (Notification answeredRequest : answeredRequests) {
           String candidateName= userRepository.findUserById(answeredRequest.getCandidate_id()).getFirstName();
           String msg="Your request to promote "+candidateName+" has been "+(answeredRequest.isApproved()?"approved":"rejected");
            NotificationRequestDTO request=new NotificationRequestDTO(answeredRequest.getId(), answeredRequest.getResponder_id(),answeredRequest.getRequester_id(),answeredRequest.getCandidate_id(),msg,answeredRequest.getDate_requested().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(),answeredRequest.getDate_answered().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(), false);
           requests.add(request);
        }
        for (Notification pendingRequest : pendingRequests) {
            String candidateName= userRepository.findUserById(pendingRequest.getCandidate_id()).getFirstName();
            String msg="Your request to promote "+candidateName+" is pending";
            NotificationRequestDTO request=new NotificationRequestDTO(pendingRequest.getId(), pendingRequest.getResponder_id(),pendingRequest.getRequester_id(),pendingRequest.getCandidate_id(),msg,pendingRequest.getDate_requested().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(),null, false);
            requests.add(request);
        }

return requests;
    }

    public ArrayList<NotificationRequestDTO> getCompanyNotifications(int CompanyID){
        ArrayList<Notification> answeredRequests= notificationRepository.getCompanyAnsweredRequests(CompanyID);
        ArrayList<Notification> pendingRequests= notificationRepository.getCompanyPendingRequests(CompanyID);
        ArrayList<NotificationRequestDTO> requests=new ArrayList<>();
        for (Notification answeredRequest : answeredRequests) {
            String responderName= userRepository.findUserById(answeredRequest.getResponder_id()).getFirstName();
            String msg="Your request to hire "+responderName+" has been "+(answeredRequest.isApproved()?"approved":"rejected");
            NotificationRequestDTO request=new NotificationRequestDTO(answeredRequest.getId(), answeredRequest.getResponder_id(),answeredRequest.getRequester_id(),answeredRequest.getCandidate_id(),msg,answeredRequest.getDate_requested().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(),answeredRequest.getDate_answered().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(), false);
            requests.add(request);
        }
        for (Notification pendingRequest : pendingRequests) {
            String responderName= userRepository.findUserById(pendingRequest.getResponder_id()).getFirstName();
            String msg="Your request to hire "+responderName+" is pending";
            NotificationRequestDTO request=new NotificationRequestDTO(pendingRequest.getId(), pendingRequest.getResponder_id(),pendingRequest.getRequester_id(),pendingRequest.getCandidate_id(),msg,pendingRequest.getDate_requested().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(),null, false);
            requests.add(request);
        }


        return requests;
    }

    public ArrayList<NotificationRequestDTO> getSuperAdminNotifications(int superAdminID){
        ArrayList<Notification> answeredRequests= notificationRepository.getSuperAdminAnsweredRequests(superAdminID);
        ArrayList<Notification> pendingRequests= notificationRepository.getSuperAdminPendingRequests(superAdminID);

        ArrayList<NotificationRequestDTO> requests=new ArrayList<>();
        for (Notification answeredRequest : answeredRequests) {
            String requesterName= userRepository.findUserById(answeredRequest.getRequester_id()).getFirstName();
            String candidateName= userRepository.findUserById(answeredRequest.getCandidate_id()).getFirstName();
            String msg="You have "+(answeredRequest.isApproved()?"approved":"rejected")+" "+ requesterName+"'s request to promote "+candidateName;
            NotificationRequestDTO request=new NotificationRequestDTO(answeredRequest.getId(), answeredRequest.getResponder_id(),answeredRequest.getRequester_id(),answeredRequest.getCandidate_id(),msg,answeredRequest.getDate_requested().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(),answeredRequest.getDate_answered().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(), false);
            requests.add(request);
        }
        for (Notification pendingRequest : pendingRequests) {
            String requesterName= userRepository.findUserById(pendingRequest.getRequester_id()).getFirstName();
            String candidateName= userRepository.findUserById(pendingRequest.getCandidate_id()).getFirstName();
            String msg="Admin "+requesterName+" has requested to promote "+candidateName;
            NotificationRequestDTO request=new NotificationRequestDTO(pendingRequest.getId(), pendingRequest.getResponder_id(),pendingRequest.getRequester_id(),pendingRequest.getCandidate_id(),msg,pendingRequest.getDate_requested().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(),null, true);
            requests.add(request);
        }

        return requests;
    }

    public ArrayList<NotificationRequestDTO> getClientNotifications(int ClientID){
        ArrayList<Notification> answeredRequests= notificationRepository.getClientAnsweredRequests(ClientID);
        ArrayList<Notification> pendingRequests= notificationRepository.getClientPendingRequests(ClientID);
        ArrayList<Notification> promotionRequests= notificationRepository.getClientPromotionNotification(ClientID);
        ArrayList<NotificationRequestDTO> requests=new ArrayList<>();
        for (Notification answeredRequest : answeredRequests) {
            String requesterName= userRepository.findUserById(answeredRequest.getRequester_id()).getFirstName();
            String msg="You have "+(answeredRequest.isApproved()?"approved":"rejected")+" "+ requesterName+"'s request to be their Agent";
            NotificationRequestDTO request=new NotificationRequestDTO(answeredRequest.getId(), answeredRequest.getResponder_id(),answeredRequest.getRequester_id(),answeredRequest.getCandidate_id(),msg,answeredRequest.getDate_requested().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(),answeredRequest.getDate_answered().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(), false);
            requests.add(request);
        }
        for (Notification pendingRequest : pendingRequests) {
            String requesterName= userRepository.findUserById(pendingRequest.getRequester_id()).getFirstName();
            String msg="Company "+requesterName+" has requested to hire you as one of their agents ";
            NotificationRequestDTO request=new NotificationRequestDTO(pendingRequest.getId(), pendingRequest.getResponder_id(),pendingRequest.getRequester_id(),pendingRequest.getCandidate_id(),msg,pendingRequest.getDate_requested().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(),null, true);
            requests.add(request);
        }
        for (Notification promotionRequest : promotionRequests) {
            String requesterName= userRepository.findUserById(promotionRequest.getRequester_id()).getFirstName();
            String msg="Congratulation, You have been promoted to Admin by "+requesterName;
            NotificationRequestDTO request=new NotificationRequestDTO(promotionRequest.getId(), promotionRequest.getResponder_id(),promotionRequest.getRequester_id(),promotionRequest.getCandidate_id(),msg,promotionRequest.getDate_requested().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(),promotionRequest.getDate_answered().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(), false);
            requests.add(request);
        }

        return requests;
    }







    public void markSeen(Notification notification){
        notification.setSeen(true);
//        notificationRepository.updateNotificationById(notification.getNotification_id(), notification );
        //mark the seen attribute true in the notification table
    }
    public List<UserPromoteDTO> searchClientUsers(String query) {
        List<User> matchingUsers = userRepository.findClientsByFirstNameOrLastName(query, UserRoleEnum.CLIENT);

        List<UserPromoteDTO> userDTOs = new ArrayList<>();

        for (User user : matchingUsers) {
            int previousRequests= (int) notificationRepository.countCandidatePreviousRequests(user.getId());
            boolean requested= previousRequests != 0;
                    // Assuming User entity has getters for first name, last name, and profile photo
            UserPromoteDTO userDTO = new UserPromoteDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getProfile_photo(), requested);
            userDTOs.add(userDTO);
        }

        return userDTOs;
    }


    


}
