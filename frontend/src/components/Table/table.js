import React from "react";
import TableHeader from "./TableHeader/tableHeader";
import TableBody from "./TableBody/tableBody";

const Table = (props) => {

    const {items} = props;

    let headers = getHeaders(items[0]);
    headers.push("Actions");
    
    return(
      <table className="table tr-history table-striped small">
          <TableHeader headers={headers}/>
          <TableBody items={items} {...props}/>
      </table>
    );
    
};

function getHeaders (item) {
    let headers = [];
    for (let prop in item){
        if(item.hasOwnProperty(prop) && prop !== 'id') {
            let firstLetter = prop.charAt(0).toLocaleUpperCase();
            let rest = prop.substring(1, prop.length);
            let final = `${firstLetter}${rest}`;
            headers.push(final);
        }
    }

    return headers;
}

export default Table;