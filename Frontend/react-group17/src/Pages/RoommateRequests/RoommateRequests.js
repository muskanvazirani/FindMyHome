import React, { useEffect } from "react";
import { useState } from "react";
import MatchEntry from "../../components/MatchEntry/MatchEntry";
import { getRoommateRequests } from "../../services/MatchService";
import EmptyData from '../../components/EmptyData/EmptyData';

const RoommateRequests = () => {

    const [isLoading, setLoading] = useState(true);
    const [users, setUsers] = useState([]);
    
    useEffect(() => {
        getRoommateRequests()
            .then(
                (response) => {
                    if(Object.keys(response.data).length !== 0){
                    console.log(response);
                    setUsers(response.data);
                    setLoading(false);
                    }
                }
            )
            .catch(
                err => console.log(err)
            )
    }, []);


    const deleteUser = id => {
        const filtered = users.filter(user => user.id !== id);
        setUsers(filtered);
    };

    const renderMatchList = () => {
        console.log(users);
        return (
            <div className="matchList">
                {users.map(user => (
                    <MatchEntry
                        user={user}
                        key={user.id}
                        onDelete={deleteUser}
                    />
                ))}
            </div>
        )
    }

    const renderEmptyLikeRequests = () => {
        return(   
            <div
                style={{
                display: "table",
                position: "absolute",
                height: "100%",
                width: "100%",
                top: 0,
                left: 0}}>
                <div
                style={{
                display: "table-cell",
                verticalAlign: "middle",
                textAlign: "center"}}>
                        <EmptyData />
                </div>
                
            </div>
            
        )
    }
    return (
        <div className="">
            { isLoading ? renderEmptyLikeRequests() : renderMatchList() }
        </div>
    );
}

export default RoommateRequests;