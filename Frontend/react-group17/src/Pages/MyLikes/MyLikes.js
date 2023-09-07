import React from 'react'
import { useState, useEffect } from "react";
import { UserPrefEntry } from '../../components/UserPrefEntry/UserPrefEntry';
import { getMyLikes } from "../../services/MatchService";
import EmptyData from '../../components/EmptyData/EmptyData';

export const MyLikes = () => {
    const [isLoading, setLoading] = useState(true);
    const [users, setUsers] = useState([]);

    useEffect(() => {
        getMyLikes()
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


    const renderMatchList = () => {
        console.log(users);
        return (<div className="matchList">
            {users.map(user => (
                <UserPrefEntry
                    user={user}
                    key={user.id}
                />
            ))}
        </div>
        )
    }
    const renderEmptyLikes = () => {
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
            {isLoading ? renderEmptyLikes() : renderMatchList()}
        </div>
    );
}
