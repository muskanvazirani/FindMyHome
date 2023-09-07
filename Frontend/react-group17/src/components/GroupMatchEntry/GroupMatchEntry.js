import React from "react";
import { useState } from "react";
import { useNavigate } from 'react-router-dom';
import '../MatchEntry/MatchEntry.css';
import "bulma/css/bulma.css";
import { likeGroup } from "../../services/GroupService";
import { GroupMatchEntryItem } from "../GroupMatchEntryItem/GroupMatchEntryItem";

const GroupMatchEntry = (props) => {
    const {group} = props
    console.log(group)
    const [showUsersInfo, showContactHandler] = useState(false);
    const [likeText, likeTextHandler] = useState("Like");
    const [redirectUserDetails, redirectUserDetailsHandler ] = useState("Full Details");
    const c = showUsersInfo ? "arrow rotate" : "arrow";

    const likeButtonHandler = () => {
        likeTextHandler("Processing..")
        const matchObject = {
            "groupId": props.group.groupID
        }
        likeGroup(matchObject)
            .then(response => {
                console.log(response);
                props.onDelete(props.group.groupID);
            }
            )
            .catch(err => {
                likeTextHandler("Error");
                console.log(err);
            });
    }
    const navigate = useNavigate();
    const redirectUserDetailsButton = () => {
        redirectUserDetailsHandler("Processing..");
        navigate(`/groupuserspref?groupID=${props.group.groupID}`,
     );
    }

    return(
        <div className="card" style={{ marginBottom: "10px" }} key={props.groupId} >
        <div className="card-content">
            <div className="media" onClick={e => showContactHandler(!showUsersInfo)} style={{ alignItems: "center", cursor: "pointer" }}>
                <div
                    className="media-left"
                    style={{
                        paddingRight: "20px",
                        marginRight: "20px",
                        borderRight: "2px solid rgba(10, 10, 10, 0.2)"
                    }}
                >
                    <figure className="image is-48x48">
                        <img
                            src="/Users/naveen/group17/Frontend/react-group17/src/components/GroupMatchEntry/law.jpeg"
                            alt="G"
                            className="is-rounded"
                        />
                    </figure>
                </div>
                <div className="media-content" style={{ overflow: "hidden", "position": "relative" }}>
                    <p className="title is-4 is-capitalized">
                        Group {props.group.groupID}
                        <span className={c} />
                    </p>

                </div>
            </div>
        </div>
        <div className="card">
            {showUsersInfo && (
                <div className="card-content">
                    {
                        props.group.users.map(group => (
                            <GroupMatchEntryItem
                            firstName={group['firstName']}
                            lastName={group['lastName']}
                            />
                        ))
                    }
                    <div className="likeButton">
                        <button className="button-23" onClick={e => likeButtonHandler()}>{likeText}</button>
                        <button className="button-23" onClick={e => redirectUserDetailsButton()}>{redirectUserDetails}</button>
                    </div>
                </div>
            )}
        </div>
    </div>
    )
}

export default GroupMatchEntry;