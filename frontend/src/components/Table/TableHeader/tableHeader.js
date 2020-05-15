import React from "react";

const TableHeader = (props) => {

    let {headers} = props;

    let ths = headers.map((value) => {
        return <th scope={"col"} key={value}>{value}</th>;
    });

    return (
        <thead>
            <tr>{ths}</tr>
        </thead>
    );

};

export default TableHeader;