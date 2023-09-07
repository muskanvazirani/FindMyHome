import React from 'react'

export const MatchEntryDetailItem = (props) => {
    return (
        <div className="panel-block">
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
                    {props.name}
                </label>{" "}
                <p>{props.value}</p>
            </div>
        </div>
    )
}
