import axios from '../custom-axios/axios';
import qs from 'qs';

const IngredientsService = {

    getAllIngredients: () => {
        return axios.get("/ingredients/all");
    },

    updateIngredient: (ingredient) => {
        let id = ingredient.id;
        let formParams = qs.stringify(ingredient);
        return axios.patch(`/ingredients/${id}`, formParams);
    },

    getIngredient: (id) => {
        return axios.get(`/ingredients/${id}`);
    },

    deleteIngredient: (id) => {
        return axios.delete(`/ingredients/${id}`);
    },

    addIngredient: (ingredient) => {
        let formParams = qs.stringify(ingredient);
        return axios.post("/ingredients", formParams);
    }

};

export default IngredientsService;