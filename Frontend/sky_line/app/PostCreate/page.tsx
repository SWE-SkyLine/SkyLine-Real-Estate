
"use client"
import { useState } from 'react';

enum EstateTypeEnum {
  APARTMENT = 'Apartment',
  HOUSE = 'House',
  OFFICE = 'Office',
  LAND = 'Land',
  OTHER = 'Other',
}

interface FormData {
  title: string;
  price: string;
  rent: boolean;
  area: string;
  description: string;
  estateType: EstateTypeEnum;
  mapLink: string;
  photos: File[];
}

const CreatePost: React.FC = () => {
  const [formData, setFormData] = useState<FormData>({
    title: '',
    price: '',
    rent: false,
    area: '',
    description: '',
    estateType: EstateTypeEnum.APARTMENT,
    mapLink: '',
    photos: [],
  });

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement | HTMLSelectElement>) => {
    const { name, value, type } = e.target;
  
    setFormData((prevData) => ({
      ...prevData,
      [name]: type === 'checkbox' ? (e.target as HTMLInputElement).checked : value,
    }));
  };
  

  const handleFileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const files = e.target.files;
    if (files) {
      setFormData((prevData) => ({
        ...prevData,
        photos: Array.from(files),
      }));
    }
  };

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    try {
      const formDataToSend = new FormData();
      Object.entries(formData).forEach(([key, value]) => {
        if (key === 'photos') {
          // Append each photo separately
          value.forEach((photo: string | Blob, index: any) => {
            formDataToSend.append(`photos[${index}]`, photo);
          });
        } else {
          formDataToSend.append(key, value as string);
        }
      });

      const response = await fetch('http://localhost:8080/api/posts', {
        method: 'POST',
        body: formDataToSend,
      });

      if (response.ok) {
        console.log('Post created successfully!');
      } else {
        console.error('Failed to create post.');
      }
    } catch (error) {
      console.error('Error:', error);
    }
  };

  return (
    <div className="container mt-4">
      <h2>Create a New Post</h2>

      {/* Post Creation Form */}
      <form onSubmit={handleSubmit}>
        {/* Title */}
        <div className="mb-3">
          <label htmlFor="title" className="form-label">
            Title
          </label>
          <input type="text" className="form-control" id="title" name="title" onChange={handleChange} required />
        </div>

        {/* Price */}
        <div className="mb-3">
          <label htmlFor="price" className="form-label">
            Price
          </label>
          <input type="number" className="form-control" id="price" name="price" onChange={handleChange} required />
        </div>

        {/* Rent Checkbox */}
        <div className="mb-3 form-check">
          <input type="checkbox" className="form-check-input" id="rent" name="rent" onChange={handleChange} />
          <label className="form-check-label" htmlFor="rent">
            For Rent
          </label>
        </div>

        {/* Area */}
        <div className="mb-3">
          <label htmlFor="area" className="form-label">
            Area
          </label>
          <input type="number" className="form-control" id="area" name="area" onChange={handleChange} required />
        </div>

        {/* Description */}
        <div className="mb-3">
          <label htmlFor="description" className="form-label">
            Description
          </label>
          <textarea
            className="form-control"
            id="description"
            name="description"
            rows={3}
            onChange={handleChange}
            required
          ></textarea>
        </div>

        {/* Estate Type */}
        <div className="mb-3">
          <label htmlFor="estateType" className="form-label">
            Estate Type
          </label>
          <select className="form-select" id="estateType" name="estateType" onChange={handleChange} required>
            {Object.values(EstateTypeEnum).map((type) => (
              <option key={type} value={type}>
                {type}
              </option>
            ))}
          </select>
        </div>

        {/* Map Link */}
        <div className="mb-3">
          <label htmlFor="mapLink" className="form-label">
            Map Link
          </label>
          <input type="text" className="form-control" id="mapLink" name="mapLink" onChange={handleChange} required />
        </div>

        {/* Photo Upload (Multiple) */}
        <div className="mb-3">
          <label htmlFor="photos" className="form-label">
            Photos
          </label>
          <input type="file" className="form-control" id="photos" name="photos" multiple onChange={handleFileChange} />
        </div>

        {/* Submit Button */}
        <button type="submit" className="btn btn-primary">
          Create Post
        </button>
      </form>
    </div>
  );
};

export default CreatePost;
