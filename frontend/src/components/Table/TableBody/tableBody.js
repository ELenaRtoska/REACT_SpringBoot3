import React from "react";
import TableRow from "../TableRow/tableRow";

const TableBody = (props) => {

    let {items} = props;
    
    let rows = items.map((item) => {
        return <TableRow item={item} key={item.id} deleteAction={props.deleteAction} {...props}/>
    });

    return( 
        <tbody>
            {rows}
        </tbody>
    );

};

export default TableBody;