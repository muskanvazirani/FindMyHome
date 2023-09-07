import axios from 'axios';


export const getToken=()=>{
    return localStorage.getItem('USER_KEY');
}

export const likeUser=(data)=>{
    return axios({
        'method':'POST',
        'url':`${process.env.REACT_APP_hostUrl||'http://localhost:8080'}/api/v1/match`,
        'data':data,
        'headers':{
            'Authorization':'Bearer '+getToken()
        }
    })
}

export const getRoommateList=()=>{
    return axios({
        'method':'GET',
        'url':`${process.env.REACT_APP_hostUrl||'http://localhost:8080'}/api/v1/get-all-matches`,
        'headers':{
            'Authorization':'Bearer '+getToken()
        }
    })
}

export const getMyLikes=()=>{
    return axios({
        'method':'GET',
        'url':`${process.env.REACT_APP_hostUrl||'http://localhost:8080'}/api/v1/likes`,
        'headers':{
            'Authorization':'Bearer '+getToken()
        }
    })
}

export const getRoommateRequests=()=>{
    return axios({
        'method':'GET',
        'url':`${process.env.REACT_APP_hostUrl||'http://localhost:8080'}/api/v1/my-roommate-request`,
        'headers':{
            'Authorization':'Bearer '+getToken()
        }
    })
}

export const getMyGroupMembers=()=>{
    return axios({
        'method':'GET',
        'url':`${process.env.REACT_APP_hostUrl||'http://localhost:8080'}/api/v1/my-group`,
        'headers':{
            'Authorization':'Bearer '+getToken()
        }
    })
}

export const getGroupRequests=()=>{
    return axios({
        'method':'GET',
        'url':`${process.env.REACT_APP_hostUrl||'http://localhost:8080'}/api/v1/group-requests`,
        'headers':{
            'Authorization':'Bearer '+getToken()
        }
    })
}

export const approveUser=(data)=>{
    return axios({
        'method':'POST',
        'url':`${process.env.REACT_APP_hostUrl||'http://localhost:8080'}/api/v1/request-approval`,
        'data':data,
        'headers':{
            'Authorization':'Bearer '+getToken()
        }
    })
}
