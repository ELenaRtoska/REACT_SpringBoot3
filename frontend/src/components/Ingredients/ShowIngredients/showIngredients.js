import React from 'react';
import {Link} from "react-router-dom";
import Table from "../../Table/table";

const ShowIngredients = (props) => {
    let tableItems = props.ingredients.length === 0 ?
        <h1>There are no ingredients!</h1> :
        <Table items={props.ingredients} {...props}/>;
    return (
        <div className={"container"}>
            <div className="row">
                <h4 className="text-upper text-left">Ingredients</h4>
                <div className="table-responsive">
                    {tableItems}
                </div>
                <Link className="btn btn-outline-secondary" to={`${props.match.url}/new`}>
                    <span><strong>Add new ingredient</strong></span>
                </Link>
            </div>
        </div>
    );
};

export default ShowIngredients;