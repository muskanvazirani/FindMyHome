import React from "react";
import { useState } from "react";
import './GroupRequestEntry.css';
import "bulma/css/bulma.css";
import { approveUser } from "../../services/MatchService";
import { MatchEntryDetailItem } from "../MatchEntryDetailItem/MatchEntryDetailItem";

const MatchEntry = (props) => {
    const [showContactInfo, showContactHandler] = useState(false);
    const [approveText, approveTextHandler] = useState("Approve");
    const c = showContactInfo ? "arrow rotate" : "arrow";

    const approveButtonHandler = () => {
        approveTextHandler("Processing..")
        const matchObject = {
            "userId": props.user.id
        }
        approveUser(matchObject)
            .then(response => {
                console.log(response);
                props.onDelete(props.user.id);
            }
            )
            .catch(err => {
                approveTextHandler("Error");
                console.log(err);
            });
    }
    return (
        <div className="card" style={{ marginBottom: "10px" }} key={props.user.firstName}>
            <div className="card-content">
                <div className="media" onClick={e => showContactHandler(!showContactInfo)} style={{ alignItems: "center", cursor: "pointer" }}>
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
                                src={props.user.profilePicBase64}
                                alt={props.user.firstName}
                                className="is-rounded"
                            />
                        </figure>
                    </div>
                    <div className="media-content" style={{ overflow: "hidden", "position": "relative" }}>
                        <p className="title is-4 is-capitalized">
                            {props.user.firstName /*.charAt(0).toUpperCase() + name.slice(1)*/}{" "}
                            {props.user.lastName /*.charAt(0).toUpperCase() + lastName.slice(1)*/}
                            <span className={c} />
                        </p>

                        <p className="subtitle is-6 is-capitalized">
                            {props.user.city}, {props.user.province}
                        </p>
                    </div>
                </div>
            </div>
            <div className="card">
                {showContactInfo && (
                    <div className="card-content">
                        <MatchEntryDetailItem
                            name="Drinker"
                            value={props['user']['Drinker']}
                        />
                        <MatchEntryDetailItem
                            name="Smoker"
                            value={props['user']['Smoker']}
                        />
                        <MatchEntryDetailItem
                            name="Meal Preference"
                            value={props['user']['Meal']}
                        />
                        <MatchEntryDetailItem
                            name="Furnished Flat Preference"
                            value={props['user']['Furnished']}
                        />
                        <MatchEntryDetailItem
                            name="Gender Preference"
                            value={props['user']['Gender']}
                        />
                        <MatchEntryDetailItem
                            name="Noise Level Preference"
                            value={props['user']['Noise level']}
                        />
                        <MatchEntryDetailItem
                            name="Lease Renewal Preference"
                            value={props['user']['Lease Type']}
                        />
                        <MatchEntryDetailItem
                            name="Age Range Preference"
                            value={props['user']['Age range']}
                        />
                        <MatchEntryDetailItem
                            name="Cleanliness"
                            value={props['user']['Cleanliness']}
                        />
                        <MatchEntryDetailItem
                            name="Parking Preference"
                            value={props['user']['Parking']}
                        />
                        <MatchEntryDetailItem
                            name="Pets Policy Preference"
                            value={props['user']['Pets Policy']}
                        />
                        <MatchEntryDetailItem
                            name="Property Type Preference"
                            value={props['user']['Property Type']}
                        />
                        <MatchEntryDetailItem
                            name="Guest Preference"
                            value={props['user']['Guests']}
                        />
                        <MatchEntryDetailItem
                            name="Prefered Number of rooms"
                            value={props['user']['Number of Rooms']}
                        />
                        <MatchEntryDetailItem
                            name="Prefered Number of bathrooms"
                            value={props['user']['Number of Bathrooms']}
                        />
                        <MatchEntryDetailItem
                            name="Dining Preference"
                            value={props['user']['Cooking']}
                        />
                        <div className="likeButton">
                            <button className="button-23" onClick={e => approveButtonHandler()}>{approveText}</button>
                        </div>
                    </div>
                )}
            </div>
        </div>
    );
}

export default MatchEntry;
