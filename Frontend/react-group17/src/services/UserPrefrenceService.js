import axios from 'axios';
import { getToken } from './AuthenticationService';

export const getUserPrefrenceOptions=()=>{
    return axios({
        'method':'GET',
        'url':`${process.env.REACT_APP_hostUrl||'http://localhost:8080'}/api/preferences/all`,
        headers:{
            'Authorization':'Bearer '+getToken()
        }
    })
}

export const saveUserPrefrences=(preferences)=>{
    return axios({
        'method':'POST',
        'url':`${process.env.REACT_APP_hostUrl||'http://localhost:8080'}/api/preferences/save`,
        'data':preferences,
        headers:{
            'Authorization':'Bearer '+getToken()
        }
    })
}

export const fetchUserPrefences=()=>{
    return axios({
        'method':'GET',
        'url':`${process.env.REACT_APP_hostUrl||'http://localhost:8080'}/api/preferences/getUserPrefValues`,
        headers:{
            'Authorization':'Bearer '+getToken()
        }
    })
}