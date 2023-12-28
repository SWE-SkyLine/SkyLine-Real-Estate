package com.example.SkyLine.controller;

import com.example.SkyLine.DTO.AuctionRetrievalDTO;
import com.example.SkyLine.DTO.BidRetrievalDTO;
import com.example.SkyLine.DTO.PostRetrievalDTO;
import com.example.SkyLine.entity.Auction;
import com.example.SkyLine.entity.FilterData;
import com.example.SkyLine.entity.Photo;
import com.example.SkyLine.entity.Post;
import com.example.SkyLine.enums.EstateTypeEnum;
import com.example.SkyLine.repository.PhotoRepository;
import com.example.SkyLine.repository.PostRepository;

import com.example.SkyLine.service.BidService;
import com.example.SkyLine.service.PostCreationService;
import com.example.SkyLine.service.PostService;
import com.example.SkyLine.utility.ContollerDataToPostAdapter;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@PreAuthorize("hasRole('CLIENT')")
public class PostController {
    @Autowired
    private PostCreationService postCreationService;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostService postService;


    @Autowired
    private BidService bidService;

    

    @PostMapping("/publish_post")
    public ResponseEntity<?> publishPost(
            @RequestParam("title") String title,
            @RequestParam("price") String price,
            @RequestParam("isRent") String isRent,
            @RequestParam("area") String area,
            @RequestParam("description") String description,
            @RequestParam("estateType") String estateType,
            @RequestParam("mapLink") String mapLink,
            @RequestParam("address") String address,
            @RequestParam("city") String city,
            @RequestParam("bedroom") String bedroom,
            @RequestParam("bathroom") String bathroom,
            @RequestParam("level") String level,
            @RequestParam("UID") String postCreatorUID,
            @RequestPart("photos") MultipartFile[] photos) {

        System.out.println("there is a request to publish a post");
        System.out.println(title + " " + price + " " + isRent + " " + area + " "
                + description + " " + estateType + " " + mapLink
                + " " + address + " " + city + " "
                + bedroom + " " + bathroom + " " + level + " " + photos.length
                + " UID :  " + postCreatorUID);

        Post post = ContollerDataToPostAdapter.contollerDataToPost(
                title, price, isRent, area, description, estateType,
                mapLink, address, city, bedroom, bathroom, level);
        int postId = postCreationService.createPost(post, photos, postCreatorUID);

        return new ResponseEntity<String>("Post Added with ID : " + postId, HttpStatus.OK);
    }

    @PostMapping("/publish_auction")
    public ResponseEntity<?> publishAuction(
            @RequestParam("title") String title,
            @RequestParam("price") String price,
            @RequestParam("isRent") String isRent,
            @RequestParam("area") String area,
            @RequestParam("description") String description,
            @RequestParam("estateType") String estateType,
            @RequestParam("mapLink") String mapLink,
            @RequestParam("address") String address,
            @RequestParam("city") String city,
            @RequestParam("bedroom") String bedroom,
            @RequestParam("bathroom") String bathroom,
            @RequestParam("level") String level,
            @RequestParam("UID") String postCreatorUID,
            @RequestPart("photos") MultipartFile[] photos,
            @RequestPart("start_time") String start_time,
            @RequestPart("end_time") String end_time,
            @RequestPart("start_bid") String start_bid

    ) {

        System.out.println("there is a request to publish a Auction");
        System.out.println(title + " " + price + " " + isRent + " " + area + " "
                + description + " " + estateType + " " + mapLink
                + " " + address + " " + city + " "
                + bedroom + " " + bathroom + " " + level + " " + photos.length
                + " UID :  " + postCreatorUID + " stime :  " + start_time + " end_time :  " + end_time
                + " min_bid :  " + start_bid);

        Auction auction = ContollerDataToPostAdapter.contollerDataToAuction(
                title, price, isRent, area, description, estateType,
                mapLink, address, city, bedroom, bathroom, level, start_time, end_time, start_bid);
        int AuctionId = postCreationService.createAuction(auction, photos, postCreatorUID);

        return new ResponseEntity<String>("AuctionId Added with ID : " + AuctionId, HttpStatus.OK);
    }

    @PostMapping("/add_bid/{client_id}/{bid_price}/{auction_id}")
    public ResponseEntity<?> addBid(@PathVariable int client_id,@PathVariable int bid_price,@PathVariable int auction_id) {
        boolean noError = bidService.addBid(client_id,bid_price,auction_id);
        if (!noError) {
            return new ResponseEntity<String>(HttpStatus.REQUEST_TIMEOUT);
        }
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @GetMapping("/get_bids/{auction_id}")
    public ResponseEntity<?> getBids(@PathVariable int auction_id) throws MalformedURLException {
        // Use auctionId in your logic, for example:
        ArrayList<BidRetrievalDTO> bids = bidService.getBidsOfAuction(auction_id);
        if(bids==null){
            return new ResponseEntity<>( HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(bids, HttpStatus.OK);
    }

    @GetMapping("/get_posts_with_photos")
    public ResponseEntity<List<PostRetrievalDTO>> getFullPosts(
            @RequestParam(required = false) String searchQuery,
            @RequestParam(required = false) Integer priceFrom,
            @RequestParam(required = false) Integer priceTo,
            @RequestParam(required = false) EstateTypeEnum estateType,
            @RequestParam(required = false) Boolean rent,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String sortOrder) throws MalformedURLException {

        List<Post> posts;

        if (searchQuery != null) {
            // Search logic
            posts = postService.search(searchQuery);
        } else if (hasFilterParameters(priceFrom, priceTo, estateType, rent)) {
            // Filter logic
            System.out.println(rent);
            posts = postService.filter(new FilterData(priceFrom, priceTo, estateType, rent));
        } else if (sortBy != null && sortOrder != null) {
            // Sort logic
            posts = postService.sort(sortBy, sortOrder);
        } else {
            // Default: Get all posts
            posts = postRepository.findAllNonAuctionPosts();
        }

        // Convert to DTOs and return
        List<PostRetrievalDTO> retrievalDTOS = postCreationService.PostToRetrievalEntity(posts);
        return new ResponseEntity<>(retrievalDTOS, HttpStatus.OK);
    }

    @GetMapping("/get_auctions_with_photos")
    public ResponseEntity<List<AuctionRetrievalDTO>> getFullauctions() throws MalformedURLException {

        List<Auction> auctions = postRepository.findAllAuctions();

        // Convert to DTOs and return
        List<AuctionRetrievalDTO> retrievalDTOS = postCreationService.AuctionToRetrievalEntity(auctions);
        return new ResponseEntity<>(retrievalDTOS, HttpStatus.OK);
    }

    private boolean hasFilterParameters(Integer priceFrom, Integer priceTo, EstateTypeEnum estateType,
            Boolean rent) {
        return priceFrom != null || priceTo != null || estateType != null || rent != null;
    }

    @PostMapping("/get_posts_for_profile_with_photos")
    public ResponseEntity<?> getPostsByUserId(
            @RequestBody Map<String, Integer> requestBody) throws MalformedURLException {

        Integer userId = requestBody.get("userId");

        if (userId == null) {
            return ResponseEntity.badRequest().body("User ID is required in the request body");
        }

        List<Post> posts = postService.getPostsByUserId(userId);

        if (posts == null) {
            return ResponseEntity.notFound().build();
        }

        List<PostRetrievalDTO> retrievalDTOS = postCreationService.PostToRetrievalEntity(posts);
        return ResponseEntity.ok(retrievalDTOS);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Post>> search(@RequestParam String query) {
        List<Post> result = postService.search(query);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/filter")
    public ResponseEntity<List<Post>> filter(@RequestBody FilterData filterData) {
        List<Post> result = postService.filter(filterData);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/sort")
    public ResponseEntity<List<Post>> sort(@RequestParam String sortBy, @RequestParam String sortOrder) {
        List<Post> result = postService.sort(sortBy, sortOrder);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
