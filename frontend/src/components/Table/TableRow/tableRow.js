import React from 'react';
import TableActions from "../TableActions/tableActions";

const TableRow = (props) => {

    let {item} = props;

    let tds = getData(item);
    tds.push(<TableActions {...props} id={item.id} deleteAction={props.deleteAction} key={"actions"}/>);

    return (
        <tr>
            {tds}
        </tr>
    );
};

function getData(item) {
    let tds = [];
    for (let prop in item){
        if(item.hasOwnProperty(prop) && prop !== 'id')
            tds.push(<td scope="col" key={prop}>{item[prop].toString()}</td>)
    }
    return tds;
}

export default TableRow;