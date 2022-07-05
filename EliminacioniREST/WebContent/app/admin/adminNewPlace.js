Vue.component("admin-newPlace", {
   
    data:function(){
        return{
          place: { name: "", type: "", description: "", status: "", workingTime: "", address: { street: "", number: 0, city: "", longitude: 0, latitude: 0, zipCode: 0}, logo: "", managerId: 0},
           managers: [],
           form: false,
           manager: { managerName: "", managerSurname: "", managerUsername: "", managerBirthday : "", gender: 0, address :{ street: "", number: 0, city: "", longitude: 0, latitude: 0, zipCode: 0} }
        }
    },
template: 
`

<div class="containerInfo"> 
    <div class="row content">
          
               <div class="col-sm-7">
               
             
                        <div class="informations" >
                            <form @submit='createPlace'>
                              <div class="row"> 
				               <div class="col-sm-2"> 
				               <label> Naziv:
				                </label>
				               </div>
				                <div class="col-sm-5"> 
				               <input class="form-control" type="text" v-model="place.name" placeholder="Naziv objekta"/>
				               </div>
				               </div>
               
			                <div class="row"> 
			                 <div class="col-sm-2"> 
              					 <label> Adresa</label>
				               </div>
			               <div class="col-sm-3"> 
              					 <input class="form-control" type="text"  v-model="place.address.street" placeholder="Ulica" id="streetID"> 
				               </div>
				                 <div class="col-sm-2"> 
             						  <input class="form-control" type="number"  v-model="place.address.number" placeholder="Broj" id="numberID"> 
				               </div>
				               </div>
                            
                                <div class="row">
                                  <div class="col-sm-2"> 
               						<label> </label>
				               </div>
			               <div class="col-sm-3"> 
             					 <input class="form-control" type="text"  v-model="place.address.city"  placeholder="Grad" id="cityID">
				              </div>
				            <div class="col-sm-2"> 
              				  <input class="form-control" type="number"  v-model="place.address.zipCode" id="zipcodeID" >
				               </div>
				             </div>
				               
				         <div class="row">
				              <div class="col-sm-2"> 
              					 <label> Koordinate: </label>
				               </div>
                          <div class="col-sm-1"> 
                              <p>gs-gd</p>
                            </div>
			               <div class="col-sm-2">            
               					<input class="form-control" type="number"  v-model="place.address.longitude" id="longitudeID" >
				             </div>
                      <div class="col-sm-2"> 
                            <input class="form-control" type="number"  v-model="place.address.latitude" id="latitudeID" >
                        </div>
				        </div>
				               
				                <div class="row" v-if="managers.length !=0">
                            <div class="col-sm-2" > 
                              <label> Menadzer: </label>
                              </div>
                            <div class="col-sm-5"> 
                                <select class="form-control" v-model="place.managerId" style="background-color: lightgray">
                                    <option  v-bind:value="m.id" style=" background-color:white; color: black" v-for="m in managers">{{m.name}} {{m.surname}}  </option>
                                  
                                </select>
                              </div>
				                </div>   

                           <div class="row" v-if="managers.length == 0" style="margin-top: 1rem; ">
                                      <div class="col-sm-2" style="background:whitesmoke; margin-left: 1rem;"> 
                                        <label> Menadzer: </label>
                                        </div>
                                      <div class="col-sm-5" style="background:whitesmoke; "> 
                                       
                                      </div>
                              </div> 

                              <div v-if="form" style="margin-left: 1rem; margin-bottom: 1rem; ">
                                  <div class="row" >
                                      <div class="col-sm-2" style="background:whitesmoke; "> 
                                        <label> Ime: </label>
                                        </div>
                                        <div class="col-sm-5" style="background:whitesmoke; "> 
                                        <input class="form-control" type="text" placeholder= "Ime menadzera" v-model="manager.managerName" >
                                        </div>
                                   </div> 
                                   <div class="row">
                                        <div class="col-sm-2" style="background:whitesmoke; "> 
                                          <label> Prezime: </label>
                                          </div>
                                        <div class="col-sm-5" style="background:whitesmoke; "> 
                                        <input class="form-control" type="text" placeholder="Prezime menadzera" v-model="manager.managerSurname">
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-3" style="background:whitesmoke; "> 
                                          <label> Korisnicko ime: </label>
                                          </div>
                                        <div class="col-sm-5" style="margin-left: -6.5rem;background:whitesmoke;"> 
                                        <input class="form-control" type="text" placeholder= "Korisnicko ime menadzera" v-model="manager.managerUsername">
                                        </div>
                                      </div>

                                      <div class="row">
                                      <div class="col-sm-3" style="background:whitesmoke;background:whitesmoke; " > 
                                        <label> Datum rodjenja: </label>
                                        </div>
                                      <div class="col-sm-5" style="margin-left: -6.5rem;background:whitesmoke;"> 
                                      <input class="form-control" type="date"  v-model="manager.managerBirthday">
                                      </div>
                                    </div>


                                      <div class="row">
                                          <div class="col-sm-2" style="background:whitesmoke; "> 
                                            <label> Pol: </label>
                                          </div>
                                        <div class="col-sm-5" style="background:whitesmoke; "> 
                                        <input type="radio" name="gender"  value="MUSKI" v-model="manager.gender"  style="margin-left: 0.2em"><small style="margin-left: 1em">Muski</small>
                                        <input type="radio" name="gender"  value="ZENSKI" v-model="manager.gender"  style="margin-left: 2em"> <small  style="margin-left: 1em">Zenski</small>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-sm-2" style="background:whitesmoke; "> 
                                          <label> Adresa: </label>
                                        </div>
                                      <div class="col-sm-3" style="background:whitesmoke; "> 
                                      <input type="text"  class="form-control" placeholder="Ulica" v-model="manager.address.street" >
                                      </div>
                                      <div class="col-sm-2" style="background:whitesmoke; "> 
                                      <input type="text" class="form-control"   style="margin-left: -0.5em" v-model="manager.address.number">
                                      </div>
                                     </div>

                                     <div class="row" >
                                     <div class="col-sm-2" style="background:whitesmoke; "> 
                                       <label> </label>
                                     </div>
                                   <div class="col-sm-3" style="background:whitesmoke; "> 
                                   <input type="text" class="form-control" placeholder="Grad" v-model="manager.address.city" >
                                   </div>
                                   <div class="col-sm-2" style="background:whitesmoke; "> 
                                   <input type="number"  class="form-control" v-model="manager.address.zipCode" style="margin-left:-0.5em">
                                   </div>
                                  </div>


                                 </div> 
				               
                          <div class="row">
                                    <div class="col-sm-2"> 
                                      <label> Tip objekta: </label>
                                      </div>
                                  <div class="col-sm-5"> 
                                      <select class="form-control" v-model="place.type" placeholder="Izaberite tip objekta" style="background-color: lightgray">
                                          <option  v-bind:value="0" style=" background-color:white; color: black">TERETANA</option>
                                          <option  v-bind:value="1" style=" background-color:white; color: black">BAZEN</option>
                                            <option  v-bind:value="2" style=" background-color:white; color: black">SPORTSKI CENTAR</option>
                                            <option  v-bind:value="3" style=" background-color:white; color: black">PLESNI STUDIO</option>
                                      </select>
                                  </div>
                          </div>
				           
                        <div class="row">
                              <div class="col-sm-2"> 
                                <label> Status objekta: </label>
                              </div>
                              <div class="col-sm-5"> 
                                    <select class="form-control" v-model="place.status" placeholder="Izaberite status objekta" style="background-color: lightgray">
                                  
                                          <option  v-bind:value="0" style=" background-color:white; color: black">OTVORENO</option>
                                          <option  v-bind:value="1" style=" background-color:white; color: black">ZATVORENO</option>
                                    </select>
                                </div>
                          </div>
				           
                        <div class="row">
                          <div class="col-sm-2"> 
                            <label> Radno vreme: </label>
                            </div>
                              <div class="col-sm-5"> 
                                <input type="text" class="form-control" v-model="place.workingTime" placeholder="od - do">
                              </div>
                        </div>
				           
                        <div class="row">
                            <div class="col-sm-2"> 
                              <label> Opis: </label>
                              </div>
                        <div class="col-sm-5"> 
                          <textarea type="text" class="form-control"  v-model="place.description" placeholder="Opis"> </textarea>
                          </div>
                      </div>
				           
                      <div class="row">
                            <div class="col-sm-2"> 
                              <label> Logo: </label>
                              </div>
                            <div class="col-sm-5"> 
                              <input type="file" onchange="encodeImageFileAsURL(this)" v-model="place.logo">
                            </div>
                      </div>
                      
				           <div class="row" style="margin-top: 3rem;">
                      <div class="col-sm-2"> </div>
				              <div class="col-sm-2"> 
              				  <button type="button" class="btn btn-success" v-on:click="create">Sacuvaj </button> 
				              </div>
                        <div class="col-sm-2"> 
                          <button type="button" class="btn btn-secondary" v-on:click="otkazi">Otkazi</button>
                        </div>
				           </div>
                                     
                              
                            </form>
                           
                        </div>
                    </div>  
                    
                    <div class="col-sm-4" style="margin-left: -15rem;margin-top: 4rem;">
                    Izaberite koordinate na mapi:
                    <div id="map" class="map" style="width: 600px;height:400px;"> </div>
                     </div>  
             </div>
        </div>
   
  
  
  </div>
`,
methods:{
    changeProfile: function(event){
      event.preventDefault()
      axios.post("/EliminacioniREST/rest/profile/changeProfile", {
      "username":''+ this.user.username,
      "name":''+ this.user.name, 
      "surname":''+ this.user.surname,  
      "street":''+ this.user.address.street, 
      "number":''+ this.user.address.number, 
      "city":''+ this.user.address.city, 
      "zipCode":''+ this.user.address.zipCode,
      "gender":''+ this.user.gender,
      "birthday":''+ this.user.birthday})
      .then(
        response => {
          router.push(`/profil`);
        } 
      )
      .catch(function(error){
        console.log(error)
    })
    },
     getAvailableManagers: function(){
     
      axios.get("/EliminacioniREST/rest/user/availableManagers")
      .then(
        response => {
          this.managers= response.data;
          console.log(this.managers);
        } 
      )
      .catch(function(error){
        console.log(error)
    })
    },
    
      create: function(event){  
      event.preventDefault()
      console.log(this.place.type),
      console.log(this.place.status)

      if(this.managers.length != 0){
        this.createPlace();
      }
      else {
        this.createPlaceWithManager();
      }
      },

      createPlace: function(){
        axios.post("/EliminacioniREST/rest/place/newPlace", {
		      "name" : ''+ this.place.name,
		      "type" : ''+ this.place.type,
		       "description" : ''+ this.place.description,
		       "status" : ''+ this.place.status,
		       "workingTime" : ''+ this.place.workingTime,
		        "street":''+ this.place.address.street, 
		      "number":''+ this.place.address.number, 
		      "city":''+ this.place.address.city, 
		      "longitude" : '' + this.place.address.longitude, 
		       "latitude" : '' + this.place.address.latitude, 
		      "zipCode":''+ this.place.address.zipCode,
		      
		       "logo" : ''+ this.place.logo,
		       "managerId" : ''+ this.place.managerId,
		    
		      }).then(
		        response => {
		         router.push(`/`);
		        } 
		      ).catch(function(error){
		        console.log(error)
		    })
      },

      createPlaceWithManager:function(){
        axios.post("/EliminacioniREST/rest/place/newPlaceWithNewManager", {
          "name" : ''+ this.place.name,
          "type" : ''+ this.place.type,
          "description" : ''+ this.place.description,
          "status" : ''+ this.place.status,
          "workingTime" : ''+ this.place.workingTime,
            "street":''+ this.place.address.street, 
          "number":''+ this.place.address.number, 
          "city":''+ this.place.address.city, 
          "longitude" : '' + this.place.address.longitude, 
          "latitude" : '' + this.place.address.latitude, 
          "zipCode":''+ this.place.address.zipCode, 
          "logo" : ''+ this.place.logo,
          "managerName" : ''+ this.manager.managerName, 
          "managerSurname" : ''+ this.manager.managerSurname, 
          "managerUsername" : ''+ this.manager.managerUsername, 
          "managerBirthday" : ''+ this.manager.managerBirthday,
          "managerGender" : ''+ this.manager.gender,
          "managerStreet" : ''+ this.manager.address.street,
          "managerNumber" : ''+ this.manager.address.number,
          "managerCity" : ''+ this.manager.address.city,
          "managerZipCode" : ''+ this.manager.address.zipCode,
        })
        .then(
          response => {
          router.push(`/`);
          } 
        )
        .catch(function(error){
          console.log(error)
      })
      },



    otkazi: function(event){
      event.preventDefault()
      router.push(`/`);
    },
    changePassword: function(){
        this.mode=true
     }, 
     
      init: function(){
        const map = new ol.Map({
          target: 'map',
          layers: [
            new ol.layer.Tile({
              source: new ol.source.OSM()
            })
          ],
          view: new ol.View({
            center: ol.proj.fromLonLat([19.41, 44.82]),
            zoom: 8
          })
        })
        
      map.on('click', function (evt) {          
        var coord = ol.proj.toLonLat(evt.coordinate);
        reverseGeocode(coord);
        this.$emit('change-address', this.address);
  })
   },
      previewMapChooseLocation: function () {
        this.previewMap = !this.previewMap;
        if (this.previewMap) {
            // Draw map on screen
            this.$nextTick(function () {
                this.init();

                // Seting some extra style for map
                let c = document.getElementById("map").childNodes;
                c[0].style.borderRadius  = '10px';
                c[0].style.border = '4px solid lightgrey';
            })
        }
      },
    
    openForm: function(){
      this.form == true;
    }
  
},
mounted(){
	this.getAvailableManagers(),

   this.$nextTick(function () {
        this.init();
    })
},
filters: {
    dateFormat: function(value, format){
        var parsed = moment(value);
        return parsed.format(format)
    }
}
});

 /**
 * From coords get real address and put that value in form. 
 * @param coords cords (x,y)
 */
function reverseGeocode(coords) {
  fetch('http://nominatim.openstreetmap.org/reverse?format=json&lon=' + coords[0] + '&lat=' + coords[1])
      .then(function (response) {
          return response.json();
      }).then(function (json) {
         /* // LATITUDE & LONGITUDE
          console.log(coords);
          document.getElementById("longitudeID").value = coords[0];
          document.getElementById("latitudeID").value = coords[1];

          // TOWN 
          console.log(json.address);
          if (json.address.city) {
              document.getElementById("townID").value = json.address.city;
          }

          // STREET
          if (json.address.street) {
              document.getElementById("streetID").value = json.address.street;
          }

          // NUMBER OF HOUSE
          if (json.address.number) {
              document.getElementById("numberID").value = json.address.number;
          }

          // ZIP CODE
          if(json.address.zipCode){
              document.getElementById("zipcodeID").value = json.address.zipCode;
          }*/

          let elem = document.getElementById("longitudeID");
            elem.value = coords[0].toFixed(2);
            elem.dispatchEvent(new Event('input'));
            
            let el = document.getElementById("latitudeID");
                el.value = coords[1].toFixed(2);
                el.dispatchEvent(new Event('input'));
                
            if (json.address.road) {
                let el = document.getElementById("streetID");
                el.value = json.address.road;
                el.dispatchEvent(new Event('input'));
            } 

            if(json.address.streetNumber){
                let el = document.getElementById("numberID");
                el.value = json.address.number;
                el.dispatchEvent(new Event('input'));
            }

            if (json.address.city) {
                let el = document.getElementById("cityID");
                el.value = json.address.city;
                el.dispatchEvent(new Event('input'));
             } else if (json.address.city_district) {
                let el = document.getElementById("cityID");
                el.value = json.address.city_district;
                el.dispatchEvent(new Event('input'));
            }
                
            if (json.address.postcode) {
                let el = document.getElementById("zipcodeID");
                el.value = json.address.postcode;
                el.dispatchEvent(new Event('input'));
            } 
              
          });

           const util = require('util')
           console.log(util.inspect(this.address, false, null, true)) 

     // });
    }