import axios from 'axios';

export const getToken=()=>{
    return localStorage.getItem('USER_KEY');
}

export const likeGroup=(data)=>{
    return axios({
        'method':'POST',
        'url':`${process.env.REACT_APP_hostUrl||'http://localhost:8080'}/api/v1/request-group`,
        'data':data,
        'headers':{
            'Authorization':'Bearer '+getToken()
        }
    })
}

export const getGroupsList=()=>{
    return axios({
        'method':'GET',
        'url':`${process.env.REACT_APP_hostUrl||'http://localhost:8080'}/api/v1/get-all-groups`,
        'headers':{
            'Authorization':'Bearer '+getToken()
        }
    })
}

export const getGroupUserList=(data)=>{
    console.log(data);
    return axios({
        'method':'POST',
        'url':`${process.env.REACT_APP_hostUrl||'http://localhost:8080'}/api/v1/get-group`,
        'data':data,
        'headers':{
            'Authorization':'Bearer '+getToken()
        }
    })
}