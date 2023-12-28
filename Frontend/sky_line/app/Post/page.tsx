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
// import publishPostRequest from '../Services/PublishPostService';
import { publishPostRequest,publishAuctionRequest, testPhotoApi } from '../Services/PublishPostService';
import { Alert } from 'react-bootstrap';
import testHttpOnlyCookie from '../Services/HttpOnlyCookieTest';


// interface FormData {
//     title: string;
//     price: string;
//     isRent: boolean;
//     area: string;
//     description: string;
//     estateType: EstateType;
//     mapLink: string;
//     bedroom: string;
//     bathroom: string;
//     level: string;
//     photos: File[];
// }
interface FormDataState {
    title: string;
    price: string;
    isRent: boolean;
    area: string;
    description: string;
    estateType: EstateType;
    mapLink: string;
    address: string,
    city: string,
    bedroom: string;
    bathroom: string;
    level: string;
    photos: File[];
    start_time:any
    end_time:any,
    start_bid:any
}


enum EstateType {
    APARTMENT = 'APARTMENT',
    HOUSE = 'HOUSE',
    VILLA = 'VILLA',
    LAND = 'LAND',
}

function Post({ userId }: { userId: string }) {
    const [showModal, setShowModal] = useState(false);
    const [isHovered, setIsHovered] = useState(false);
    const [isValid, setIsValid] = useState(true);
    const [isSend, setIsSend] = useState(0);
    const [Auction, setAuction] = useState(false);


    //console.log("userid: " + userId)

    const [formData, setFormData] = useState<FormDataState>({
        title: '',
        price: '',
        isRent: false,
        area: '',
        description: '',
        estateType: EstateType.APARTMENT,
        mapLink: '',
        address: '',
        city: '',
        bedroom: '',
        bathroom: '',
        level: '',
        photos: [],
        start_time:'',
        end_time:'2023-05-08',
        start_bid:''
    });
    const handleButtonClick = () => {
        setShowModal(true);
    };

    const handleCloseModal = () => {
        setShowModal(false);
    };


    const handlePost = async (event: React.FormEvent<HTMLFormElement>) => {
        // Close the open modal 
        setShowModal(false);
        // Post sending
        event.preventDefault();
        let data = new FormData();
        data.append(`UID`, userId)
        for (const key in formData) {
            console.log(key + " : values :");
            if (key === 'photos' && Array.isArray(formData.photos)) {
                console.log(formData.photos);
                formData.photos.forEach((photo) => {
                    data.append(`photos`, photo);
                });
            } else {
                console.log(formData[key as keyof FormDataState] as string + " ")
                data.append(key, formData[key as keyof FormDataState] as string);
            }
        }
        let res:any
        if(Auction==true){
            res = await publishAuctionRequest(data);
        }
        else{
            res = await publishPostRequest(data);

        }
        console.log("post request formData : " + data.get(`UID`));
        console.log(data.values);

        if (typeof res === 'object' && 'status' in res) {
            console.log(res.status);
            if (res.status === 200) {
                setIsSend(1); // Display successful message
            } else {
                setIsSend(2); // Display error message
            }
        } else {
            setIsSend(2); // Display error message
            console.error(res); // Log the error message
        }
    };

    const handleChangeTitle = (event: React.ChangeEvent<HTMLInputElement>) => {
        setFormData(prevState => ({
            ...prevState,
            title: event.target.value
        }));
        // setTitle(event.target.value);
    };

    const handlePriceChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const value = event.target.value;
        setFormData(prevState => ({
            ...prevState,
            price: event.target.value
        }));
        //setPrice(value);
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
        console.log("changed")
        setFormData(prevState => ({
            ...prevState,
            estateType: event.target.value as EstateType
        }));
        //setType(event.target.value);
    };
    const handleAreaChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setFormData(prevState => ({
            ...prevState,
            area: event.target.value
        }));
        //setArea(event.target.value);
    };

    const handleLocationLinkChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setFormData(prevState => ({
            ...prevState,
            mapLink: event.target.value
        }));
        //setLocationLink(event.target.value);
    };

    const handleBathroomChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setFormData(prevState => ({
            ...prevState,
            bathroom: event.target.value
        }));
        //setBathroom(event.target.value);
    };

    const handleBedroomChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setFormData(prevState => ({
            ...prevState,
            bedroom: event.target.value
        }));
        //setBedroom(event.target.value);
    };

    const handleLevelChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setFormData(prevState => ({
            ...prevState,
            level: event.target.value
        }));
        //setLevel(event.target.value);
    };
    
    const handleEndDate = (event: React.ChangeEvent<HTMLInputElement>) => {
        setFormData(prevState => ({
            ...prevState,
            end_time: event.target.value,
            start_time: new Date().toISOString().split('T')[0]
        }));
        //setLevel(event.target.value);
    };
    const handleMinBid = (event: React.ChangeEvent<HTMLInputElement>) => {
        setFormData(prevState => ({
            ...prevState,
            start_bid: event.target.value
        }));
        //setLevel(event.target.value);
    };

    const handleDescChange = (event: React.ChangeEvent<HTMLTextAreaElement>) => {
        setFormData(prevState => ({
            ...prevState,
            description: event.target.value
        }));
        //setDesc(event.target.value);
    };

    const handleRentOrBuyChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        console.log("changed")
        setFormData(prevState => ({
            ...prevState,
            isRent: event.target.value === 'rent'
        }));
        //setDesc(event.target.value);
    };
    const handlePostAuction = (event: React.ChangeEvent<HTMLInputElement>) => {
        console.log("changed")
        setAuction(event.target.value === 'Auction')
        //setDesc(event.target.value);
    };

    const handleAdressChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setFormData(prevState => ({
            ...prevState,
            address: event.target.value
        }));
        //setLevel(event.target.value);
    };
    const handleCityChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setFormData(prevState => ({
            ...prevState,
            city: event.target.value
        }));
        //setLevel(event.target.value);
    };

    const handleSecurity = async (event: React.MouseEvent<HTMLButtonElement, MouseEvent>) => {
        try {
          const res = await testHttpOnlyCookie();
        } catch (error) {
        }
      };
    return (
        <div>
            <button onClick={handleSecurity} style={{position:"relative", marginTop:"50px"} }>
                zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz
            </button>
            <button className={style['create-post-button']} onClick={handleButtonClick}>
                <span>Post / Auction</span>
                <CreateIcon className={style['create-icon']} />

            </button>
            {isSend === 1 ? (
                <Alert
                    variant={'success'}
                    dismissible
                    onClose={() => setIsSend(0)}
                    style={{
                        position: 'fixed',
                        bottom: '0',
                        right: '0',
                        width: '30%',
                        borderRadius: '10px',
                    }}
                >
                    Your post is published successfully
                </Alert>
            ) : isSend === 2 ? (
                <Alert
                    variant={'danger'}
                    dismissible
                    onClose={() => setIsSend(0)}
                    style={{
                        position: 'fixed',
                        bottom: '0',
                        right: '0',
                        width: '30%',
                        borderRadius: '10px',
                    }}
                >
                    Something wrong happened
                </Alert>
            ) : null}

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

                    <form onSubmit={handlePost}>
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
                                                value={formData.estateType}
                                                label="Type *"
                                                onChange={(event: SelectChangeEvent<EstateType>) => handleChangeType(event)}
                                            >
                                                <MenuItem value={EstateType.APARTMENT}>Apartment</MenuItem>
                                                <MenuItem value={EstateType.HOUSE}>House</MenuItem>
                                                <MenuItem value={EstateType.VILLA}>Villa</MenuItem>
                                                <MenuItem value={EstateType.LAND}>Land</MenuItem>

                                            </Select>
                                            <FormHelperText>Required</FormHelperText>
                                        </FormControl>
                                    </Grid>
                                    <Grid item xs={12} sm={6}>
                                        <FormControl>
                                            <RadioGroup
                                                defaultValue="Sale"
                                                name="radio-buttons"
                                                onChange={(event: React.ChangeEvent<HTMLInputElement>) => handleRentOrBuyChange(event)}
                                            >
                                                <FormControlLabel value="sale" control={<Radio />} label="Sale" />
                                                <FormControlLabel value="rent" control={<Radio />} label="Rent" />
                                            </RadioGroup>
                                        </FormControl>
                                    </Grid>
                                    <Grid item xs={12} sm={6}>
                                        <FormControl>
                                            <RadioGroup
                                                defaultValue="Post"
                                                name="radio-buttons-group"
                                                onChange={(event: React.ChangeEvent<HTMLInputElement>) => handlePostAuction(event)}
                                            >
                                                <FormControlLabel value="Post" control={<Radio />} label="Post" />
                                                <FormControlLabel value="Auction" control={<Radio />} label="Auction" />
                                            </RadioGroup>
                                        </FormControl>
                                    </Grid>
                                    <Grid item xs={12} sm={6}>
                                        <InputLabel htmlFor="outlined-adornment-amount">Price</InputLabel>
                                        <OutlinedInput
                                            id="outlined-adornment-amount"
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
                                    <Grid item xs={12} sm={3}>
                                        <TextField
                                            required
                                            label="Location Link"
                                            fullWidth
                                            variant="outlined"
                                            onChange={handleLocationLinkChange}
                                        />
                                    </Grid>

                                    <Grid item xs={12} sm={6}>
                                        <TextField
                                            required
                                            label="Adress"
                                            fullWidth
                                            variant="outlined"
                                            onChange={handleAdressChange}
                                        />
                                    </Grid>
                                    <Grid item xs={12} sm={3}>
                                        <TextField
                                            required
                                            label="City"
                                            fullWidth
                                            variant="outlined"
                                            onChange={handleCityChange}
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
                                    {Auction && <Grid item xs={6} sm={4}>
                                       <InputLabel htmlFor="outlined-adornment-date">Date</InputLabel>
                                        <OutlinedInput
                                            id="outlined-adornment-date"
                                            type="date"  // Set the type to "date" for date input
                                            onChange={handleEndDate}
                                            fullWidth
                                         />
                                   </Grid>}
                                   {Auction && <Grid item xs={6} sm={4}>
                                       <InputLabel htmlFor="outlined-adornment-date">Minimum  Bid</InputLabel>
                                        <OutlinedInput
                                            id="outlined-adornment-date"
                                            type="text"  // Set the type to "date" for date input
                                            onChange={handleMinBid}
                                            fullWidth 
                                            label="Minimum  Bid"                                           
                                         />
                                   </Grid>}
                                                               
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
                                                required
                                                onChange={handleFileChange}
                                            />
                                        </div>
                                    </Grid>
                                </Grid>
                                <div style={{ display: 'flex' }}>
                                    <Grid item xs={12} sm={6} style={{ margin: '10px' }}>
                                        <Button variant="outlined" fullWidth onClick={handleCloseModal} style={{ margin: '10px' }}>
                                            Cancel
                                        </Button>
                                    </Grid>
                                    <Grid item xs={12} sm={6} style={{ margin: '10px' }}>
                                        <Button variant="contained" fullWidth endIcon={<SendIcon />} type="submit" style={{ margin: '10px' }}>
                                            Publish
                                        </Button>
                                    </Grid>
                                </div>
                            </React.Fragment>
                        </div>
                    </form>

                </Modal>
            </CSSTransition>
        </div>
    );
};

export default Post;