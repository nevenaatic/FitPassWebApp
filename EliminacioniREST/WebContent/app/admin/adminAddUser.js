Vue.component("add-user", {
   
    data:function(){
        return{
         user: { name: "", surname: "", gender: 0, birthday: "", street: "", number: 0, city: "", zipCode: 0, username: "", role: 0}
        }
    },
template: 
`

<div class="containerInfo"> 
    <div class="row content">
          
    
               
             
                        <div class="informations" >
                            
                      
                                  <div class="row" >
                                      <div class="col-sm-2" > 
                                        <label> Ime: </label>
                                        </div>
                                        <div class="col-sm-3" > 
                                        <input class="form-control" type="text" placeholder= "Ime korisnika" v-model="user.name" >
                                        </div>
                                   </div> 
                                   
                                   <div class="row">
                                        <div class="col-sm-2"> 
                                          <label> Prezime: </label>
                                          </div>
                                        <div class="col-sm-3" > 
                                        <input class="form-control" type="text" placeholder="Prezime " v-model="user.surname">
                                        </div>
                                    </div>
                                    
                                    <div class="row">
                                        <div class="col-sm-2" > 
                                          <label> Korisnicko ime: </label>
                                          </div>
                                        <div class="col-sm-3"> 
                                        <input class="form-control" type="text" placeholder= "Korisnicko ime " v-model="user.username">
                                        </div>
                                      </div>

                                      <div class="row">
                                      <div class="col-sm-2"  > 
                                        <label> Datum rodjenja: </label>
                                        </div>
                                      <div class="col-sm-3" > 
                                      <input class="form-control" type="date"  v-model="user.birthday">
                                      </div>
                                    </div>


                                      <div class="row">
                                          <div class="col-sm-2" > 
                                            <label> Pol: </label>
                                          </div>
                                        <div class="col-sm-3" > 
                                        <input type="radio" name="gender"  value="MUSKI" v-model="user.gender"  style="margin-left: 0.2em"><small style="margin-left: 1em">Muski</small>
                                        <input type="radio" name="gender"  value="ZENSKI" v-model="user.gender"  style="margin-left: 2em"> <small  style="margin-left: 1em">Zenski</small>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-sm-2" > 
                                          <label> Adresa: </label>
                                        </div>
                                      <div class="col-sm-2" > 
                                      <input type="text"  class="form-control" placeholder="Ulica" v-model="user.street" >
                                      </div>
                                      <div class="col-sm-1" > 
                                      <input type="text" class="form-control"   style="margin-left: -0.5em" v-model="user.number">
                                      </div>
                                     </div>

                                     <div class="row" >
                                     <div class="col-sm-2" > 
                                       <label> </label>
                                     </div>
                                   <div class="col-sm-2" > 
                                   <input type="text" class="form-control" placeholder="Grad" v-model="user.city" >
                                   </div>
                                   <div class="col-sm-1" > 
                                   <input type="number"  class="form-control" v-model="user.zipCode" style="margin-left:-0.5em">
                                   </div>
                                  </div>


                               
				               
                          <div class="row">
                                    <div class="col-sm-2"> 
                                      <label> Vrsta korisnika: </label>
                                      </div>
                                  <div class="col-sm-3"> 
                                      <select class="form-control" style="background-color: lightgray" v-model="user.role">
                                          <option  v-bind:value="2" style=" background-color:white; color: black">Menadzer</option>
                                          <option  v-bind:value="3" style=" background-color:white; color: black">Trener</option>
                                       
                                      </select>
                                  </div>
                          </div>
				           
                        
				  
				 
				           <div class="row" style="margin-top: 3rem;">
                      <div class="col-sm-2"> </div>
				              <div class="col-sm-2"> 
              				  <button type="button" class="btn btn-success" v-on:click="sacuvaj()" >Sacuvaj </button> 
				              </div>
                        <div class="col-sm-2"> 
                          <button type="button" class="btn btn-secondary" v-on:click="otkazi">Otkazi</button>
                        </div>
				           </div>
                                     
                              
                        
                           
                        </div>
                 
                    
                 
             </div>
        </div>
   
  
  
  </div>
`,
methods:{

 


    otkazi: function(event){
      event.preventDefault()
      router.push(`/`);
    },
 
    sacuvaj: function(){
     axios.post("/EliminacioniREST/rest/user/createUser", {
     "name": ''+ this.user.name,
     "surname" : ''+ this.user.surname,
     "gender" :  this.user.gender,
     "birthday":  this.user.birthday,
     "street": ''+ this.user.street,
      "number": this.user.number,
       "city": ''+ this.user.city,
        "zipCode": this.user.zipCode,
         "username": ''+ this.user.username,
          "role":  this.user.role,
     }).then(response => {
      router.push(`/korisnici`);
     }).catch(function(error){
          console.log(error)
      })
    }
   
  
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

