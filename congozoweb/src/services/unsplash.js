// ES Modules syntax
import Unsplash, { toJson } from 'unsplash-js';

const unsplash = new Unsplash({ accessKey: "drqv7u6cXo3BIbeaiV2xPnysG5e1pVTsEdi5wcebNNs" });

export const getSearchedPhotos = async param => {
    return unsplash.search.photos(param.query, param.page, 6)
        .then(toJson)
        .then(json => {
            // Your code
            console.log(json);
            return json.results;
        });
};


