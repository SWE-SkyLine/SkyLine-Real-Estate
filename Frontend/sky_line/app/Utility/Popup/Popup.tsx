// components/ExampleModal.tsx
'use client'
import React, { useState } from 'react';
import styles from "./popup.module.css"
import { useRouter } from "next/navigation";


function Popup_respone({showModal,setShowModal,title,body,btn_text,btn_action}:any) {
//   const [showModal, setShowModal] = useState(true);
  const handleClose = () => setShowModal(false);

  return (
    <>
      {showModal && (
        <div className={`${styles.customModal} modal fade show`} tabIndex={-1} style={{ display: 'block' }} role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true" >
          <div className="modal-dialog modal-dialog-centered" role="document">
            <div className={`modal-content ${styles.customcontent}`} >
              <div className={`${styles.customheader} modal-header`}>
                <h5 className={`modal-title ${styles.customTitle}`} id="exampleModalLongTitle">
                  {title}
                </h5>
                <button type="button" className={`close ${styles.customClose}`} aria-label="Close" onClick={handleClose}>
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
              <hr />
              <div className={`modal-body ${styles.customBody}`}>
                {body}
              </div>
              <hr />
              <div className={`modal-footer ${styles.customFooter}`}>
                <button type="button" className={`${styles.popup_btn}`} onClick={btn_action}>
                  {btn_text}
                </button>
              </div>
            </div>
          </div>
        </div>
      )}
    </>

  );
};


function Pop_addbid({showModal,setShowModal,title,setinput,btn_text,btn_action}:any) {
  //   const [showModal, setShowModal] = useState(true);
    const handleClose = () => setShowModal(false);

    const handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
      setinput(event.target.value);
    };
  
  
    return (
      <>
        {showModal && (
          <div className={`${styles.customModal} modal fade show`} tabIndex={-1} style={{ display: 'block' }} role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true" >
            <div className="modal-dialog modal-dialog-centered" role="document">
              <div className={`modal-content ${styles.customcontent}`} >
                <div className={`${styles.customheader} modal-header`}>
                  <h5 className={`modal-title ${styles.customTitle}`} id="exampleModalLongTitle">
                    {title}
                  </h5>
                  <button type="button" className={`close ${styles.customClose}`} aria-label="Close" onClick={handleClose}>
                    <span aria-hidden="true">&times;</span>
                  </button>
                </div>
                <hr />
                <div className={`modal-body ${styles.customBody}`}>
                  <label >Add Bid </label>
                  <input className={styles.TextBox} type="text" onChange={handleInputChange} placeholder='Add Your Bid' style={{marginTop:"1rem"}}/>
                </div>
                <hr />
                <div className={`modal-footer ${styles.customFooter}`}>
                  <button type="button" className={`${styles.popup_btn}`} onClick={btn_action}>
                    {btn_text}
                  </button>
                </div>
              </div>
            </div>
          </div>
        )}
      </>
  
    );
  };

  function Popu_show_score({showModal,setShowModal,title,bids,myid}:any) {
    //   const [showModal, setShowModal] = useState(true);
      const handleClose = () => setShowModal(false);
      let router = useRouter();

    
    
      return (
        <>
          {showModal && (
            <div className={`${styles.customModal} modal fade show`} tabIndex={-1} style={{ display: 'block' }} role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true" >
              <div className="modal-dialog modal-dialog-centered" role="document">
                <div className={`modal-content ${styles.customcontent}`} >
                  <div className={`${styles.customheader} modal-header`}>
                    <h5 className={`modal-title ${styles.customTitle}`} id="exampleModalLongTitle">
                      {title}
                    </h5>
                    <button type="button" className={`close ${styles.customClose}`} aria-label="Close" onClick={handleClose}>
                      <span aria-hidden="true">&times;</span>
                    </button>
                  </div>
                  <hr />
                  <div className={`modal-body ${styles.customBody}`}>
                  <ul>
                   {bids.map((bid:any) => (
                    <li key={bid.id} className="bid-item" style={{color:'black'}}>
                     
                     <button style={{all:"unset",cursor:'pointer'}} onClick={()=>{router.push(`/profilePageViewOnly?id=${bid.client_id}&user=${myid}`)}}> <u style={{fontWeight:"600"}}>{bid.client}</u> </button>

                     {'=>'} {bid.bid_price}
                    </li>
                     ))}
                  </ul>
                   
                  </div>
                  <hr />
                  <div className={`modal-footer ${styles.customFooter}`}>
                  </div>
                </div>
              </div>
            </div>
          )}
        </>
    
      );
    };
  

export { Popup_respone, Pop_addbid,Popu_show_score };