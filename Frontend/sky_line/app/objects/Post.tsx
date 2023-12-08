'use client'
import { useState } from 'react';
import * as React from 'react';
import Modal from 'react-modal';
import FormControlLabel from '@mui/material/FormControlLabel';
import Checkbox from '@mui/material/Checkbox';
import FormControl from '@mui/material/FormControl';
import TextField from '@mui/material/TextField';
import InputLabel from '@mui/material/InputLabel';
import { Grid,  OutlinedInput, InputAdornment, FormHelperText } from '@mui/material';
import Select, { SelectChangeEvent } from '@mui/material/Select';
import MenuItem from '@mui/material/MenuItem';
import Radio from '@mui/material/Radio';
import RadioGroup from '@mui/material/RadioGroup';
import FormLabel from '@mui/material/FormLabel';
import Button from '@mui/material/Button';
import SendIcon from '@mui/icons-material/Send';
import Stack from '@mui/material/Stack';

const AppPost: React.FC = () => {
    const [showModal, setShowModal] = useState(false);
    const [isHovered, setIsHovered] = useState(false);
    const [isValid, setIsValid] = useState(true);

    //data to be sent to backend
    const [title, setTitle] = useState('');
    const [price, setPrice] = useState('');
    const [type, setType] = useState('');
    const [area, setArea] = useState('')
    const [locationLink, setLocationLink] = useState('')
    const [bathroom, setBathroom] = useState('')
    const [bedroom, setBedroom] = useState('')
    const [level, setLevel] = useState('')
    const [desc, setDesc] = useState('')



    const handleButtonClick = () => {
        setShowModal(true);
    };

    const handleCloseModal = () => {
        setShowModal(false);
    };

    const handlePost = () => {
        // Handle post logic here
    };

    const handleChangeTitle = (event: React.ChangeEvent<HTMLInputElement>) => {
        setTitle(event.target.value);
    };

    const handlePriceChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const value = event.target.value;
        setPrice(value);
        setIsValid(value === '' || parseInt(value) > 10);
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
            <button
                className="circle-button"
                style={{
                    background: 'linear-gradient(to right, blue, cyan)',
                    width: '50px',
                    height: '50px',
                    borderRadius: '50%',
                    border: 'none',
                    color: 'white',
                }}
                onClick={handleButtonClick}
            >
                +
            </button>

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
                <div style={{
                    display: 'flex',
                    flexDirection: 'column',
                    justifyContent: 'center',
                    alignItems: 'center'
                }}>
                    <h2 style={{ margin: '15px', fontFamily: 'Georgia, serif', textAlign: 'center', fontWeight: 'bold', fontSize: '2em' }}>POST YOUR ADD</h2>
                </div>

                <div style={{
                    display: 'flex',
                    flexDirection: 'column',
                    justifyContent: 'center',
                    alignItems: 'center',
                }}>
                    <React.Fragment>
                        <Grid container spacing={7}>
                            <Grid item xs={12} md={6}>
                                <TextField
                                    required
                                    label="Title"
                                    fullWidth
                                    variant="outlined"
                                    onChange={handleChangeTitle}
                                />
                            </Grid>
                            <Grid>
                                <FormControl required sx={{ m: 1, minWidth: 120 }}>
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
                            <Grid>
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
                            <Grid item xs={12} md={6}>
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
                            <Grid item xs={12} md={6}>
                                <TextField
                                    required
                                    label="Area (m²)"
                                    fullWidth
                                    variant="outlined"
                                    onChange={handleAreaChange}
                                />
                            </Grid>
                            <Grid item xs={12} md={6}>
                                <TextField
                                    required
                                    label="Location Link"
                                    fullWidth
                                    variant="outlined"
                                    onChange={handleLocationLinkChange}
                                />
                            </Grid>
                            <div style={{ display: 'flex', justifyContent: 'center' }}>
                                <Grid item xs={6} md={4}>
                                    <TextField
                                        required
                                        label="Bedrooms"
                                        fullWidth
                                        variant="outlined"
                                        onChange={handleBedroomChange}
                                    />
                                </Grid>
                                <Grid item xs={6} md={4}>
                                    <TextField
                                        required
                                        label="Bathrooms"
                                        fullWidth
                                        variant="outlined"
                                        onChange={handleBathroomChange}
                                    />
                                </Grid>
                                <Grid item xs={6} md={4}>
                                    <TextField
                                        required
                                        label="Level"
                                        fullWidth
                                        variant="outlined"
                                        onChange={handleLevelChange}
                                    />
                                </Grid>
                            </div>
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
                            <Grid container justifyContent="center">
                                <div>
                                    <Button variant="outlined" onClick={handleCloseModal} style={{ margin: '10px' }}>
                                        Close
                                    </Button>
                                    <Button variant="contained" endIcon={<SendIcon />} onClick={handlePost} style={{ margin: '10px' }}>
                                        Send
                                    </Button>
                                </div>
                            </Grid>
                        </Grid>
                    </React.Fragment>
                </div>
            </Modal>
        </div>
    );
};

export default AppPost;
