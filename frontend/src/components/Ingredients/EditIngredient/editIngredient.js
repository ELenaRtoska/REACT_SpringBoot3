import React, {useEffect, useState} from 'react';
import {useParams, useHistory} from "react-router-dom";
import IngredientsService from "../../../service/ingredientsAxios";

const EditIngredient = (props) => {

    let {id} = useParams();
    let history = useHistory();
    let [ingredient, setIngredient] = useState({
        name: '',
        amount: '',
        spicy: false,
        veggie: false
    });

    useEffect(() => {
        IngredientsService.getIngredient(id).then((response) => {
            setIngredient(response.data);
        });
    }, []);

    let handleInputChange = (e) => {
        let prop = e.target.name;
        let value;
        if(e.target.type === 'text')
            value = e.target.value;
        else
            value = e.target.checked;

        setIngredient({
            ...ingredient,
            [prop]: value
        });
    };

    let resetValues = (e) => {
        e.preventDefault();
        setIngredient({
            name: '',
            amount: '',
            spicy: false,
            veggie: false
        });
    };

    let cancelEdit = () => {
        history.push("/ingredients");
    };

    let submitHandle = (e) => {
          e.preventDefault();
          props.updateIngredient(ingredient);
    };

    return (
        <div className="row">
            <form className="card" onSubmit={submitHandle}>
                <h4 className="text-left">Edit Ingredient</h4>
                <div className="form-group row">
                    <label htmlFor="ingredient" className="col-sm-4 offset-sm-1 text-left">Ingredient name</label>
                    <div className="col-sm-6">
                        <input type="text"
                               className="form-control"
                               name="name"
                               placeholder="Name"
                               value={ingredient.name}
                               onChange={handleInputChange}
                        />
                    </div>
                </div>
                <div className="form-group row">
                    <label htmlFor="amount" className="col-sm-4 offset-sm-1 text-left">Amount</label>
                    <div className="col-sm-6">
                        <input type="text"
                               className="form-control"
                               name="amount"
                               placeholder="Amount"
                               value={ingredient.amount}
                               onChange={handleInputChange}
                        />
                    </div>
                </div>
                <div className="form-group row">
                    <label htmlFor="veggie" className="col-sm-4 offset-sm-1 text-left">Veggie</label>
                    <div className="col-sm-6 col-xl-4">
                        <input type="checkbox"
                               className="form-control"
                               name="veggie"
                               checked={ingredient.veggie}
                               onChange={handleInputChange}
                        />
                    </div>
                </div>

                <div className="form-group row">
                    <label htmlFor="spicy" className="col-sm-4 offset-sm-1 text-left">Spicy</label>
                    <div className="col-sm-6 col-xl-4">
                        <input type="checkbox"
                               className="form-control"
                               name="spicy"
                               checked={ingredient.spicy}
                               onChange={handleInputChange}
                        />
                    </div>
                </div>

                <div className="form-group row">
                    <div
                        className="offset-sm-1 col-sm-3  text-center">
                        <button
                            type="submit"
                            className="btn btn-primary text-upper"
                            disabled={ingredient.name === '' || ingredient.amount === '' ||  ingredient.name.length > 50 || ingredient.amount.length > 50}
                        >
                            Save
                        </button>
                    </div>
                    <div
                        className="offset-sm-1 col-sm-3  text-center">
                        <button
                            className="btn btn-warning text-upper"
                            onClick={resetValues}>
                            Reset
                        </button>
                    </div>
                    <div
                        className="offset-sm-1 col-sm-3  text-center">
                        <button
                            className="btn btn-danger text-upper"
                            onClick={cancelEdit}
                        >
                            Cancel
                        </button>
                    </div>
                </div>
            </form>
        </div>
    );
};

export default EditIngredient;