import axios from 'axios';


export const getToken=()=>{
    return localStorage.getItem('USER_KEY');
}

export const userLogin=(authRequest)=>{
    return axios({
        'method':'POST',
        'url':`${process.env.REACT_APP_hostUrl||'http://localhost:8080'}/api/v1/auth/authenticate`,
        'data':authRequest
    })
}

export const userRegister=(authRequest)=>{
    return axios({
        'method':'POST',
        'url':`${process.env.REACT_APP_hostUrl||'http://localhost:8080'}/api/v1/auth/register`,
        'data':authRequest
    })
}

export const fetchUserData=()=>{
    return axios({
        method:'GET',
        url:`${process.env.REACT_APP_hostUrl||'http://localhost:8080'}/api/v1/auth/demo`,
        headers:{
            'Authorization':'Bearer '+getToken()
        }
    })
}

export const checkUserLogin = () => {
    return axios({
        method:'GET',
        url:`${process.env.REACT_APP_hostUrl||'http://localhost:8080'}/api/v1/auth/demo`,
        headers:{
            'Authorization':'Bearer '+getToken()
        }

    })
}