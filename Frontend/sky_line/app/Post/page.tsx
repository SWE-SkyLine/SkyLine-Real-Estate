'use client'
import { useState } from 'react';
import * as React from 'react';
import Modal from 'react-modal';
import FormControlLabel from '@mui/material/FormControlLabel';
import Checkbox from '@mui/material/Checkbox';
import FormControl from '@mui/material/FormControl';
import TextField from '@mui/material/TextField';
import InputLabel from '@mui/material/InputLabel';
import { Grid, OutlinedInput, InputAdornment, FormHelperText } from '@mui/material';
import Select, { SelectChangeEvent } from '@mui/material/Select';
import MenuItem from '@mui/material/MenuItem';
import Radio from '@mui/material/Radio';
import RadioGroup from '@mui/material/RadioGroup';
import FormLabel from '@mui/material/FormLabel';
import Button from '@mui/material/Button';
import SendIcon from '@mui/icons-material/Send';
import Stack from '@mui/material/Stack';
import CreateIcon from '@mui/icons-material/Create';
import style from '../Post/page.module.css'
import { CSSTransition } from 'react-transition-group';
import publishPostRequest from '../Services/PublishPostService';


interface FormData {
    title: string;
    price: string;
    rent: boolean;
    area: string;
    description: string;
    estateType: EstateType;
    mapLink: string;
    bedroom: string;
    bathroom: string;
    level: string;
    photos: File[];
}

enum EstateType {
    APARTMENT = 'Apartment',
    HOUSE = 'House',
    VILLA = 'Villa',
    LAND = 'Land',
}

const Post: React.FC = () => {
    const [showModal, setShowModal] = useState(false);
    const [isHovered, setIsHovered] = useState(false);
    const [isValid, setIsValid] = useState(true);

    const [title, setTitle] = useState('');
    const [price, setPrice] = useState('');
    const [type, setType] = useState('');
    const [area, setArea] = useState('')
    const [locationLink, setLocationLink] = useState('')
    const [bathroom, setBathroom] = useState('')
    const [bedroom, setBedroom] = useState('')
    const [level, setLevel] = useState('')
    const [desc, setDesc] = useState('')
    const [rent, setRent] = useState('')
    const [photos, setPhotos] = useState<Array<any>>([]);

    const [formData, setFormData] = useState<FormData>({
        title: '',
        price: '',
        rent: false,
        area: '',
        description: '',
        estateType: EstateType.APARTMENT,
        mapLink: '',
        bedroom: '',
        bathroom: '',
        level: '',
        photos: [],
    });
    const handleButtonClick = () => {
        setShowModal(true);
    };

    const handleCloseModal = () => {
        setShowModal(false);
    };

    const handlePost = async (event: React.MouseEvent<HTMLButtonElement>) => {
        event.preventDefault();
        let data = new FormData();
        
        const res = await publishPostRequest(data);
        if((res as Response).status ==200){
          window.location.assign('/')
        }
        else{
          alert("User is not registered")
        }
    };

    const handleChangeTitle = (event: React.ChangeEvent<HTMLInputElement>) => {
        setTitle(event.target.value);
    };

    const handlePriceChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const value = event.target.value;
        setPrice(value);
        setIsValid(value === '' || parseInt(value) > 10);
    };

    //this is related to the photos
    const handleFileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const files = e.target.files;
        if (files) {
            setFormData((prevData) => ({
                ...prevData,
                photos: Array.from(files),
            }));
        }
    };

    const handleChangeType = (event: SelectChangeEvent) => {
        setType(event.target.value);
    };
    const handleAreaChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setArea(event.target.value);
    };

    const handleLocationLinkChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setLocationLink(event.target.value);
    };

    const handleBathroomChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setBathroom(event.target.value);
    };

    const handleBedroomChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setBedroom(event.target.value);
    };

    const handleLevelChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setLevel(event.target.value);
    };

    const handleDescChange = (event: React.ChangeEvent<HTMLTextAreaElement>) => {
        setDesc(event.target.value);
    };


    return (
        <div>
            <button className={style['create-post-button']} onClick={handleButtonClick}>
                <span>Create Post</span>
                <CreateIcon className={style['create-icon']} />

            </button>

            <CSSTransition
                in={showModal}
                timeout={500}
                classNames={{
                    enter: style['modal-enter'],
                    enterActive: style['modal-enter-active'],
                    exit: style['modal-exit'],
                    exitActive: style['modal-exit-active'],
                }}
                unmountOnExit
            >
                <Modal
                    isOpen={showModal}
                    onRequestClose={handleCloseModal}
                    contentLabel="Modal"
                >
                    <button
                        onClick={handleCloseModal}
                        onMouseEnter={() => setIsHovered(true)}
                        onMouseLeave={() => setIsHovered(false)}
                        style={{
                            position: 'absolute',
                            top: '10px',
                            right: '10px',
                            background: 'transparent',
                            border: 'none',
                            fontSize: '1.5em',
                            cursor: 'pointer',
                            color: isHovered ? 'black' : 'grey',
                            boxShadow: isHovered ? '0px 0px 20px rgba(0, 0, 0, 0.5)' : 'none',
                        }}
                    >
                        X
                    </button>
                    <div style={{ textAlign: 'center', marginBottom: '20px' }}>
                        <h2 style={{ fontFamily: 'Arial, sans-serif', fontWeight: 'bold', fontSize: '2em', color: '#333' }}>
                            Post Your Ad
                        </h2>
                    </div>

                    <div style={{
                        display: 'flex',
                        flexDirection: 'column',
                        justifyContent: 'center',
                        alignItems: 'center',
                    }}>
                        <React.Fragment>
                            <Grid container spacing={3}>
                                <Grid item xs={12}>
                                    <TextField
                                        required
                                        label="Title"
                                        fullWidth
                                        variant="outlined"
                                        onChange={handleChangeTitle}
                                    />
                                </Grid>
                                <Grid item xs={12} sm={6}>
                                    <FormControl required sx={{ minWidth: 120 }}>
                                        <InputLabel id="demo-simple-select-required-label">Type</InputLabel>
                                        <Select
                                            value={type}
                                            label="Type *"
                                            onChange={handleChangeType}
                                        >
                                            <MenuItem >Apartment</MenuItem>
                                            <MenuItem >House</MenuItem>
                                            <MenuItem >Villa</MenuItem>
                                            <MenuItem >Land</MenuItem>
                                        </Select>
                                        <FormHelperText>Required</FormHelperText>
                                    </FormControl>
                                </Grid>
                                <Grid item xs={12} sm={6}>
                                    <FormControl>
                                        <RadioGroup
                                            defaultValue="Sale"
                                            name="radio-buttons-group"
                                        >
                                            <FormControlLabel value="sale" control={<Radio />} label="Sale" />
                                            <FormControlLabel value="rent" control={<Radio />} label="Rent" />
                                        </RadioGroup>
                                    </FormControl>
                                </Grid>
                                <Grid item xs={12} sm={6}>
                                    <InputLabel htmlFor="outlined-adornment-amount">Price</InputLabel>
                                    <OutlinedInput
                                        id="outlined-adornment-amount"
                                        value={price}
                                        onChange={handlePriceChange}
                                        startAdornment={<InputAdornment position="start">$</InputAdornment>}
                                        label="Price"
                                        error={!isValid}
                                    />
                                    {!isValid && <FormHelperText error>Value must be greater than 10$</FormHelperText>}
                                </Grid>
                                <Grid item xs={12} sm={6}>
                                    <TextField
                                        required
                                        label="Area (m²)"
                                        fullWidth
                                        variant="outlined"
                                        onChange={handleAreaChange}
                                    />
                                </Grid>
                                <Grid item xs={12} sm={6}>
                                    <TextField
                                        required
                                        label="Location Link"
                                        fullWidth
                                        variant="outlined"
                                        onChange={handleLocationLinkChange}
                                    />
                                </Grid>
                                <Grid item xs={6} sm={4}>
                                    <TextField
                                        required
                                        label="Bedrooms"
                                        fullWidth
                                        variant="outlined"
                                        onChange={handleBedroomChange}
                                    />
                                </Grid>
                                <Grid item xs={6} sm={4}>
                                    <TextField
                                        required
                                        label="Bathrooms"
                                        fullWidth
                                        variant="outlined"
                                        onChange={handleBathroomChange}
                                    />
                                </Grid>
                                <Grid item xs={6} sm={4}>
                                    <TextField
                                        required
                                        label="Level"
                                        fullWidth
                                        variant="outlined"
                                        onChange={handleLevelChange}
                                    />
                                </Grid>
                                <Grid item xs={12}>
                                    <TextField
                                        required
                                        label="Description"
                                        fullWidth
                                        variant="outlined"
                                        multiline
                                        rows={4}
                                        onChange={handleDescChange}
                                    />
                                </Grid>
                                <Grid item xs={12} sm={6}>
                                    {/* Photo Upload (Multiple) */}
                                    <div className="mb-3">
                                        <label htmlFor="photos" className="form-label">
                                            Photos
                                        </label>
                                        <input
                                            type="file"
                                            className="form-control"
                                            id="photos"
                                            name="photos"
                                            multiple
                                            onChange={handleFileChange}
                                        />
                                    </div>
                                </Grid>
                            </Grid>
                            <Grid item xs={12} sm={6}>
                                <Button variant="contained" fullWidth endIcon={<SendIcon />} onClick={handlePost} style={{ margin: '10px' }}>
                                    Submit
                                </Button>
                            </Grid>
                            <Grid item xs={12} sm={6}>
                                <Button variant="outlined" fullWidth onClick={handleCloseModal} style={{ margin: '10px' }}>
                                    Cancel
                                </Button>
                            </Grid>

                        </React.Fragment>


                    </div>
                </Modal>
            </CSSTransition>
        </div>
    );
};

export default Post;