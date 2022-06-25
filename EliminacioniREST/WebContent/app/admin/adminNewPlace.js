Vue.component("admin-newPlace", {
   
    data:function(){
        return{
          place: { name: "", type: "", description: "", status: "", workingTime: "", address: { street: "", number: 0, city: "", longitude: 0, latitude: 0, zipCode: 0}, logo: ""}
           
        }
    },
template: 
`
<div class="containerInfo"> 
 <div class="row content">
          
               <div class="col-sm-9">
                        <div class="informations" >
                            <form @submit='createPlace'>
                                    <table>
                                        <tr>
                                            <td> Naziv: </td>
                                            <td> <input class="form-control" type="text" v-model="place.name" placeholder="Naziv objekta"/> </td>
                                        </tr>
                                        <tr> 
                                            <td>Adresa: </td>
                                            <td> <input class="form-control" type="text"  v-model="place.address.street" placeholder="Ulica"> </td>
                                            <td> <input class="form-control" type="number"  v-model="place.address.number" placeholder="Broj"> </td>
                                              <td> <input class="form-control" type="text"  v-model="place.address.city"  placeholder="Grad"> </td>
                                        </tr>
                                         <tr> 
                                            <td> </td>
                                      
                                 
                                        </tr>
                                           <tr> 
                                            <td> Koordinate </td>
                                            <td> <input class="form-control" type="number"  v-model="place.address.longitude" > </td>
                                            <td> <input class="form-control" type="number"  v-model="place.address.latitude" > </td>
                                            <td> <input class="form-control" type="number"  v-model="place.address.zipCode" > </td>
                                        </tr>
                                        <tr> 
                                            <td>Tip objekta:</td>
                                            <td>
                                            <select class="form-control" v-model="place.type" placeholder="Izaberite tip objekta">
                    <option  v-bind:value="0" style=" background-color:white; color: black">TERETANA</option>
                     <option  v-bind:value="1" style=" background-color:white; color: black">BAZEN</option>
                      <option  v-bind:value="2" style=" background-color:white; color: black">SPORTSKI CENTAR</option>
                       <option  v-bind:value="3" style=" background-color:white; color: black">PLESNI STUDIO</option>
                </select> </td>
                                        </tr>
                                        <tr> 
                                            <td> Status:</td>
                                            <td>
                                                                                        <select class="form-control" v-model="place.status" placeholder="Izaberite status objekta">
                    <option  v-bind:value="0" style=" background-color:white; color: black">OTVORENO</option>
                     <option  v-bind:value="1" style=" background-color:white; color: black">ZATVORENO</option>
                      
                </select>
                                            </td>
                                            
                                        </tr>
                                        <tr> 
                                            <td>Radno vreme: </td>
                                            <td> <input type="text" class="form-control" v-model="place.workingTime"> </td>
                                        </tr>
                                        <tr> 
                                            <td> Opis:</td>
                                            <td> <input class="form-control" type="text" placeholder="ulica"  v-model="place.description"  > </td>
                                         
                                          </tr> 
                                         <tr> 
                                          <td> Logo: </td> 
                                          <td><input type="file" onchange="encodeImageFileAsURL(this)" v-model="place.logo"></td>
                                           
                                        </tr>
                                     
                                        
                                    </table> 
                                        <div v-if="mode" stylep="top-margin:5px;">
                                    
                                        </div>
                                        <table>   
                                        <tr style="height:10px;"> </tr>
                                             <tr>  <td><button type="button" class="btn btn-success" v-on:click="createPlace">Sacuvaj </button> </td>
                                             <td style="width:15px"> </td>       
                                             <td>  <button type="button" class="btn btn-secondary" v-on:click="otkazi">Otkazi</button></td>
                                             </tr>
                                        </table>
                               
                            </form>
                           
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
        createPlace: function(event){
        
      event.preventDefault()
      console.log(this.place.type),
      console.log(this.place.status),
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
    
      })
      .then(
        response => {
        //  router.push(`/`);
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
},
mounted(){
    
},
filters: {
    dateFormat: function(value, format){
        var parsed = moment(value);
        return parsed.format(format)
    }
}
});