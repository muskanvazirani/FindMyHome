import axios from 'axios';
import { getToken } from './AuthenticationService';

export const getUserDetails=()=>{
    return axios({
        'method':'GET',
        'url':`${process.env.REACT_APP_hostUrl||'http://localhost:8080'}/api/v1/profilepage`,
        headers:{
            'Authorization':'Bearer '+getToken()
        }
    })
}

export const saveUserDetails=(editedUserDetails)=>{
    return axios({
        'method':'POST',
        'url':`${process.env.REACT_APP_hostUrl||'http://localhost:8080'}/api/v1/save`,
        'data':editedUserDetails,
        headers:{
            'Authorization':'Bearer '+getToken()
        }
    })
}