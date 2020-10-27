<template>
    <div class="map-container">
        <div id="map" ref="myMap"></div>
    </div>
</template>

<script>
    import olms from "ol-mapbox-style";
    import * as ol from "ol";
    import * as proj from 'ol/proj';

    export default {
        props: {
            searchLocation: Object
        },
        data () {
            return {
                longitude: 16.3738,
                latitude: 48.2082,
                zoom: 13
            }
        },
        methods: {
            createMap() {
                var key = 'pk.349f95f3dff13624c697cab4b5c5057f';   //Insert your LocationIQ access token here

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
            }
        },
        watch: {
            searchLocation: function () {
                this.longitude = this.searchLocation.lon;
                this.latitude = this.searchLocation.lat;
                this.zoom = 16;
                this.createMap();
                /*
                let self = this;
                setTimeout(function () {
                    self.search();
                    }, 1000);
                 */
            }
        },
        mounted: function () {
            this.createMap();
        }
    }
</script>

<style lang="scss">
    @import '~ol/ol.css';

    #map {
        position: absolute;
        top: 2280px;
        width: 636px;
        height: 300px;
        border: 1px solid #F0A002;
        border-radius: 10px;

        canvas {
            border-radius: 15px;
        }
    }
</style>

