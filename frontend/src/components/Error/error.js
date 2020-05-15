import React from 'react';

const Error = (props) => {
    return (
        <div className={"container"}>
            <div className={"text-center"}>
                <h1>{props.error.response.status}</h1>
                <p>{props.error.response.data.message}</p>
                <p>{props.error.response.data.trace}</p>
            </div>
        </div>
    );
};

export default Error;