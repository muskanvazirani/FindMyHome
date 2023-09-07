import axios from 'axios';
import { getToken } from './AuthenticationService';

export const saveListing=(Listing)=>{
    return axios({
        'method':'POST',
        'url':`${process.env.REACT_APP_hostUrl||'http://localhost:8080'}/api/v1/listing`,
        'data':Listing,
        headers:{
            'Authorization':'Bearer '+getToken()
        }
        
    })
}

export const showListing=()=>{
    return axios({
        'method':'GET',
        'url':`${process.env.REACT_APP_hostUrl||'http://localhost:8080'}/api/v1/getAll`,
        headers:{
            'Authorization':'Bearer '+getToken()
        }
    })
}

export const likeListing=(data)=>{
    return axios({
        'method':'POST',
        'url':`${process.env.REACT_APP_hostUrl||'http://localhost:8080'}/api/v1/like-listing`,
        'data': data,
        headers:{
            'Authorization':'Bearer '+getToken()
        }
    })
}

export const unlikeListing=(data)=>{
    return axios({
        'method':'POST',
        'url':`${process.env.REACT_APP_hostUrl||'http://localhost:8080'}/api/v1/unlike-listing`,
        'data': data,
        headers:{
            'Authorization':'Bearer '+getToken()
        }
    })
}

export const getMyListings=()=>{
    return axios({
        'method':'GET',
        'url':`${process.env.REACT_APP_hostUrl||'http://localhost:8080'}/api/v1/get-my-listings`,
        headers:{
            'Authorization':'Bearer '+getToken()
        }
    })
}

export const getLikedListings=()=>{
    return axios({
        'method':'GET',
        'url':`${process.env.REACT_APP_hostUrl||'http://localhost:8080'}/api/v1/get-liked-listings`,
        headers:{
            'Authorization':'Bearer '+getToken()
        }
    })
}

export const getLikedUsers=()=>{
    return axios({
        'method':'GET',
        'url':`${process.env.REACT_APP_hostUrl||'http://localhost:8080'}/api/v1/get-liked-listingUsers`,
        headers:{
            'Authorization':'Bearer '+getToken()
        }
    })
}