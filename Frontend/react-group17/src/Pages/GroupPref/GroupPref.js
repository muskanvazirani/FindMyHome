import React, { useState, useEffect } from "react";
import { useSearchParams } from "react-router-dom";
import GroupPrefEntry from "../../components/GroupPrefEntry/GroupPrefEntry";
import { getGroupUserList } from "../../services/GroupService";
import EmptyData from '../../components/EmptyData/EmptyData';

const GroupPref = () => {

    const [isLoading, setLoading] = useState(true);
    const [groupUserDetails, setGroupUserDetails] = useState();
    const [searchParams] = useSearchParams();
    const code = searchParams.get('groupID'); 
    //console.log(code);

    useEffect(() => {
        const data = {
            "groupId": code
        }
        getGroupUserList(data)
            .then(
                (response) => {
                    if(Object.keys(response.data).length !== 0){
                    console.log(response);
                    setGroupUserDetails(response.data);
                    setLoading(false);
                }
            }
            )
            .catch(
                err => console.log(err)
            )
            // eslint-disable-next-line
    }, [code]);


    const renderGroupPref = () =>{
        return(
            
            <div className="matchList">
            {groupUserDetails.map(user => (
                <GroupPrefEntry
                    user={user}
                    key={user.id}
                />
            ))}
        </div>
        
  )
}

const renderEmptyGroupsPref = () => {
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


    return(
        <div className="">
            { isLoading ? renderEmptyGroupsPref() : renderGroupPref() } 
        </div>
    );
}

export default GroupPref;