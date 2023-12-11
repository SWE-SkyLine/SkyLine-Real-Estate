import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import style from "./page.module.css";

interface DynamicImageProps {
  src: string;
  alt: string;
}

// const DynamicImage: React.FC<DynamicImageProps> = ({ src, alt }) => {
//   return <img src={src} alt={alt} />;
// };

const CardComponent: React.FC = () => {
  // Assuming 'login.jpg' is in the 'public/assets' directory
  const x = '/assets/login.jpg';

  return (
    <> 
      <div className="card">
      <div className={`${style.imgs}`} style={{ backgroundImage: `url(${x})` }}></div>
        <div className="card-body">
          <h5 className="card-title">Card title</h5> 
          <p className="card-text">
            This is a wider card with supporting text below as a natural lead-in to additional content. This content is a
            little bit longer.
          </p>
          <p className="card-text">
            <small className="text-muted">Last updated 3 mins ago</small>
          </p>
        </div>
      </div>
    </>
  );
};

export default CardComponent;
