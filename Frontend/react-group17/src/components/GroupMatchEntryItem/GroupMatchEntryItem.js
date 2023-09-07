import React from "react";

export const GroupMatchEntryItem = (props) => {
    return (
        <div className="group-block">
            <div className="content">
                <label
                    className="label has-text-right"
                    style={{
                        display: "inline-block",
                        paddingRight: "20px",
                        marginRight: "20px",
                        marginBottom: "0"
                    }}
                >
                {props.firstName} {props.lastName}
                <hr/>
            </label>{" "}
            </div>
        </div>
    )
}