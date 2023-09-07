import React from "react";
import { useState, useEffect } from "react";
import { fetchUserData } from "../../services/AuthenticationService";

const Home = () => {
    const [userName, setUserName] = useState('');
    useEffect(() => {
        fetchUserData()
            .then((response)=>{
                console.log(response);
                setUserName(response.data);
            })
            .catch(err => console.log(err));
      });
    return (
        <div className="form">
            Hello {userName}
        </div>      
    );
}

export default Home;
