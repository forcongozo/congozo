import olms from "ol-mapbox-style";
import * as ol from "ol";
import * as proj from 'ol/proj';

export const renderMap = async () => {
    const key = 'pk.349f95f3dff13624c697cab4b5c5057f';   //Insert your LocationIQ access token here

    let self = this;

    document.getElementById('map').innerHTML = '';

    olms('map','https://tiles.locationiq.com/v2/streets/vector.json?key='+key).then(function(map) {

        //Set the view for this map.
        map.setView(new ol.View({
            center: proj.fromLonLat([self.longitude, self.latitude]),
            zoom: self.zoom
        }));

        // [16.3738, 48.2082]

        /*
        //to create a marker
        var marker1 = new ol.Feature({
            geometry: new ol.geom.Point(
                proj.fromLonLat([16.3738, 48.2082])
            ),
        });

        //to enhance style and add icon to the map
        marker1.setStyle(new ol.style.Style({
            image: new ol.style.Icon({
                scale: 0.5,               //scale to adjust the proportion of the icon
                src: 'marker.png',           //link of the icon
            })
        }));

        //to create a marker
        var marker2 = new ol.Feature({
            geometry: new ol.geom.Point(
                proj.fromLonLat([16.3738, 48.2082])
            ),
        });

        //to enhance style and add icon to the map
        marker2.setStyle(new ol.style.Style({
            image: new ol.style.Icon({
                scale: 0.5,                 //scale to adjust the proportion of the icon
                src: 'marker50px.png',             //link to the icon
            })
        }));

        //Let’s include the markers and create a vector source with it
        var vectorSource = new ol.source.Vector({
            features: [marker1, marker2]
        });

        //Let’s create a vector layer, with a source created above
        var vectorLayer = new ol.layer.Vector({
            source: vectorSource,
        });

        map.addLayer(vectorLayer);

         */
    });
};
