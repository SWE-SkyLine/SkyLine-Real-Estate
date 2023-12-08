// components/ExampleModal.tsx
'use client'
import React, { useState } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import styles from "./popup.module.css"


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

export {Popup_respone}