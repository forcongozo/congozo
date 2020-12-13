<template>
    <div class="congozo-container congozo-bg-main">
        <div class="main-container">
            <div class="back-button">
                <button class="back-btn" @click="back"><i class="fas fa-arrow-left"/><span>Zurück</span></button>
            </div>
            <div class="form-group no-border">
                <div class="search-input-container">
                    <label for="search-photo"/>
                    <input
                            :disabled="dataLoading"
                            v-model="param.query"
                            type="text"
                            class="form-control"
                            id="search-photo"
                            placeholder="Fotos suchen mit Unsplash"
                    />
                    <button :disabled="dataLoading" type="button" @click="searchPhoto">
                        <i class="fas fa-search"/>
                    </button>
                </div>
                <div class="search-photos-container" v-if="this.unsplashData.length !== 0">
                    <ul class="search-photos-list">
                        <li v-for="photo in unsplashData" :key="photo.id" class="list-item">
                            <img :src="photo.urls.regular"  alt="photo" class="photo-item"/>
                            <div class="overlay"></div>
                            <button @click="addPhoto(photo)"><i class="fas fa-plus-circle"/></button>
                        </li>
                    </ul>
                    <div class="page-buttons-container">
                        <button @click="previousPage"><i class="fas fa-angle-double-left"/></button>
                        <button @click="nextPage"><i class="fas fa-angle-double-right"/></button>
                    </div>
                </div>
                <span v-html="credits" />
            </div>
            <div class="form-group">
                <div class="add-photos-container">
                    <label for="add-photo">
                        <i class="fas fa-images"/>
                        <p>Eigene Fotos hochladen</p>
                    </label>
                    <input
                            type="file"
                            id="add-photo"
                            alt="photo input"
                            accept="image/*"
                            multiple
                            @change="uploadPhoto"
                    />
                </div>
                <div class="my-photos-container" v-if="this.experience.unsplashPhotos.length !== 0 || this.experience.myPersonalPhotos.length !== 0">
                    <ul class="my-photos-list" id="my-photos-list-id">
                        <li v-for="photo in experience.unsplashPhotos" :key="photo.id" class="list-item">
                            <img :src="photo.urls.regular"  alt="photo" class="photo-item"/>
                            <div class="overlay"></div>
                            <button @click="removeUnsplashImage(photo)"><i class="fas fa-minus-circle"/></button>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="continue-button">
                <button class="congozo-btn-main btn-block" @click="submit">Speichern und Weiter</button>
            </div>
        </div>
    </div>
</template>

<script>
    import { getSearchedPhotos } from "../services/unsplash";

    export default {
        name: "ExperienceFifth",
        props: {
            initExperience: {
                unsplashPhotos: Array,
                myPersonalPhotos: Array
            }
        },
        data() {
            return {
                param: {
                    query: '',
                    page: 1
                },
                experience: {
                    unsplashPhotos: this.initExperience.unsplashPhotos || [],
                    myPersonalPhotos: this.initExperience.myPersonalPhotos || []
                },
                dataLoading: false,
                unsplashData: []
            }
        },
        computed: {
            imageUrl() {
                // if (this.unsplashData) return this.unsplashData.urls.regular;
                return null;
            },
            credits() {
                /*var html = null;
                if (this.unsplashData) {
                    html = "Photo by ";
                    html +=
                        "<a href='" +
                        this.unsplashData.user.links.html +
                        "' target='_blank'>";
                    html += this.unsplashData.user.name;
                    html += "</a>";
                    html += " on ";
                    html +=
                        "<a href='" + this.unsplashData.links.html + "' target='_blank'>";
                    html += "Unsplash";
                    html += "</a>";
                }
                return html;*/
                return null;
            }
        },
        methods: {
            back() {
                this.$emit('nextPage', 4);
            },
            submit() {
                this.$validator.validate().then(valid => {
                    if (valid) {
                        this.$emit('nextPage', 6);
                        this.$emit('experienceInfo', this.experience)
                    }
                    else {
                        this.handleValidationErrorAdvanced();
                    }
                });
            },
            handleValidationErrorAdvanced () {
                const firstField = Object.keys(this.errors.collect())[0];

                this.$refs[`${firstField}Input`].scrollIntoView();
            },
            searchPhoto() {
                this.dataLoading = true;
                getSearchedPhotos(this.param).then(res => {
                    console.log(res);
                    this.unsplashData = res;
                    this.dataLoading = false;
                });
            },
            addPhoto(photo) {
                if (!this.maximumNumberOfPhotos()) {
                    this.experience.unsplashPhotos.push(photo);
                }
                console.log(photo)
            },
            uploadPhoto(e) {
                if (!this.maximumNumberOfPhotos()) {
                    let file = e.target.files[0];
                    this.experience.myPersonalPhotos.push(file);
                    const self = this;

                    let reader = new FileReader();
                    reader.onload = function (e) {
                        let previewDiv = document.getElementById('my-photos-list-id');
                        let li = document.createElement('v-li');
                        li.setAttribute('class', 'list-item');
                        li.setAttribute('id', file.name + '-1');
                        li.innerHTML =
                            "<img class='thumbnail' src='" + e.target.result + "' title='" + file.name + "' alt='image preview'/>"
                            + "<div class='overlay'></div>"
                            + "<button id='" + file.name + "'><i class='fas fa-minus-circle'/></button>";
                        previewDiv.appendChild(li);
                        self.removeUploadedImage(file.name);
                    };
                    reader.readAsDataURL(file);
                }
            },
            removeUploadedImage (name) {
                const self = this;
                document.getElementById(name).addEventListener('click', function () {
                    self.experience.myPersonalPhotos.forEach((item, index) => {
                        if (item.name === name) {
                            self.experience.myPersonalPhotos.splice(index, 1);
                            let elem = document.getElementById(name + '-1');
                            elem.remove()
                        }
                    })
                })
            },
            removeUnsplashImage(photo) {
                this.experience.unsplashPhotos.forEach((item, index) => {
                    if (item.id === photo.id) {
                        this.experience.unsplashPhotos.splice(index, 1);
                    }
                })
            },
            previousPage() {
                if (this.param.page > 1) {
                    this.param.page -= 1;
                    this.searchPhoto();
                }
            },
            nextPage() {
                this.param.page += 1;
                this.searchPhoto();
            },
            maximumNumberOfPhotos() {
                return (this.experience.unsplashPhotos.length + this.experience.myPersonalPhotos.length) >= 5;
            }
        }
    }
</script>

<style scoped lang="scss">
    @import '../style/congozo.css';

    .main-container {
        width: 100%;
        max-width: 600px;
        margin: auto;
        height: auto;
        padding: 3rem 2rem;

        .form-group {
            border-bottom: solid 1px #cdcdcd;

            .form-control {
                height: 3rem;
                max-width: 600px;
                margin: auto auto 1rem auto;
                border-radius: 20px;
            }
        }

        .no-border {
            border: none;
        }

        .search-input-container {
            display: flex;

            input {
                border-top-right-radius: 0 !important;
                border-bottom-right-radius: 0 !important;
            }

            button {
                height: 3rem;
                width: 4rem;
                border-top-right-radius: 20px;
                border-bottom-right-radius: 20px;
                border: 1px solid #ced4da;
                outline: none;
            }

            button:focus {
                border: none;
            }

            .fa-search {
                color: #F0A002;
                font-size: 20px;
            }
        }

        .search-photos-container {

            .search-photos-list {
                display: flex;
                flex-wrap: wrap;
                justify-content: space-between;
                padding: 10px 0;
                border: 1px solid #ced4da;
                background-color: white;

                .list-item {
                    min-width: 150px;
                    max-width: 180px;
                    margin: 5px auto;
                    padding: 0 5px;
                    position: relative;

                    img {
                        display: block;
                        margin: auto;
                        max-height: 100px;
                        width: auto;
                    }

                    // hover button
                    .overlay {
                        position: absolute;
                        top: 0;
                        left: 0;
                        background: rgba(0, 0, 0, 0);
                        transition: background 0.5s ease;
                    }

                    button {
                        position: absolute;
                        top: 30px;
                        left: 47px;
                        text-align: center;
                        opacity: 0;
                        transition: opacity .35s ease;
                        background-color: transparent;
                        border: none;
                        display: inline-block;
                        outline: none;
                        pointer-events: none;
                    }

                    .fa-plus-circle {
                        text-align: center;
                        color: #F0A002;
                        font-size: 40px;
                        margin: auto 10px;
                        z-index: 1;
                    }
                }

                .list-item:hover button {
                    opacity: 1;
                    pointer-events: all;
                }

                .list-item:hover {
                    opacity: 0.8;
                }
            }

            .page-buttons-container {
                display: flex;
                justify-content: space-between;

                button, button:focus {
                    border: none;
                    outline: 0;
                    background-color: #F4F2F0;
                }

                .fa-angle-double-left, .fa-angle-double-right {
                    color: #F0A002;
                    font-size: 40px;
                    margin: auto 10px;
                }
            }
        }

        .add-photos-container {
            padding: 20px 0;
            input {
                display: none;
            }

            label {
                width: 50%;
                height: 200px;
                margin: auto;
                text-align: center;
                display: flex;
                flex-direction: column;
                border: 1px solid #ced4da;
                border-radius: 10px;
                background-color: white;
                cursor: pointer;

                .fa-images {
                    font-size: 50px;
                    margin: 60px auto 10px auto;
                }
            }
        }

        .my-photos-container {
            padding: 10px 0;
            border: 1px solid #ced4da;
            background-color: white;

            .my-photos-list {
                display: flex;
                flex-wrap: wrap;
                flex-direction: row;
                margin: 0;

                .list-item {
                    min-width: 150px;
                    max-width: 180px;
                    margin: 5px auto;
                    position: relative;
                    padding: 0 5px;

                    img {
                        display: block;
                        margin: auto;
                        max-height: 100px;
                        width: auto;
                    }

                    // hover button
                    .overlay {
                        position: absolute;
                        top: 0;
                        left: 0;
                        background: rgba(0, 0, 0, 0);
                        transition: background 0.5s ease;
                    }

                    button {
                        position: absolute;
                        top: 30px;
                        left: 47px;
                        text-align: center;
                        opacity: 0;
                        transition: opacity .35s ease;
                        background-color: transparent;
                        border: none;
                        display: inline-block;
                        outline: none;
                        pointer-events: none;
                    }

                    .fa-minus-circle {
                        text-align: center;
                        color: #F0A002;
                        font-size: 40px;
                        margin: auto 10px;
                        z-index: 1;
                    }
                }

                .list-item:hover button {
                    opacity: 1;
                    pointer-events: all;
                }

                .list-item:hover {
                    opacity: 0.8;
                }
            }
        }
    }

    .back-button {
        padding: 0 10px;
        height: 50px;

        .back-btn {
            float: right;
            padding: 5px;
            border: solid 1px grey;
            border-radius: 10px;

            svg, span {
                margin: auto 5px;
            }
        }
    }

    .continue-button {
        margin-top: 30px;
    }
</style>

