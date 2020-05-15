import React from 'react';
import {Link} from "react-router-dom";

const TableActions = ({match, id, deleteAction}) => {
    return (
        <td scope="col">
            <Link className="btn btn-sm btn-outline-secondary" to={`${match.url}/${id}/edit`}>
                <span className="fa fa-edit"/>
                <span><strong>Edit</strong></span>
            </Link>
            <button className="btn btn-sm btn-outline-secondary" onClick={() => deleteAction(id)}>
                <span className="fa fa-remove"/>
                <span><strong>Remove</strong></span>
            </button>
            <Link className="btn btn-sm btn-outline-dark" to={`${match.url}/${id}/details`}>
                <span><strong>Details</strong></span>
            </Link>
        </td>
    );
};

export default TableActions;