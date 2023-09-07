import React, { useEffect } from "react";
import { useState } from "react";
import { getGroupsList } from "../../services/GroupService";
// import { useState } from "react";
// import { getRoommateList } from "../../services/MatchService";
import GroupMatchEntry from "../../components/GroupMatchEntry/GroupMatchEntry";
import './group.css';
import EmptyData from '../../components/EmptyData/EmptyData';

const GroupMatch = () => {

    const [isLoading, setLoading] = useState(true);
    const [groups, setGroups] = useState([]);

    useEffect(() => {
        getGroupsList()
            .then(
                (response) => {
                    if(Object.keys(response.data).length !== 0){
                    response.data.sort((a,b) => b.SimilarityScore - a.SimilarityScore);
                    console.log(response);
                    setGroups(response.data);
                    setLoading(false);
                    }
                }
            )
            .catch(
                err => console.log(err)
            )
    }, []);


    const deleteGroup = groupID => {
        const filtered = groups.filter(group => group.groupID !== groupID);
        setGroups(filtered);
    };

    const renderGroupMatchList = () => {
        console.log(groups);
        return (
            <div className="matchList">
                {groups.map(user => (
                    <GroupMatchEntry
                        group= { user }
                        onDelete={deleteGroup}
                    />
                ))}
            </div>
        )
    }


    const renderEmptyGroupData = () => {
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
            { isLoading ? renderEmptyGroupData() : renderGroupMatchList() }
        </div>
    );
}

export default GroupMatch;
