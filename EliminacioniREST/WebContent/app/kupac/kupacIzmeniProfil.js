Vue.component("izmeniProfil-kupac", {
   
    data: function() {
        return{
        user: null,
        mode: false}
    },
template: 
`
<section> 
         <div class="containerInfo">     
          
            <div class="row content">
                <div class="col-sm-3 sidenav" style="margin-left: 10%">
                    <h3  style="width: 200px;"><small>Vase informacije na profilu:</small> <hr> </h3>
                    <img class= "img-responsive"src="../pictures/korisnik.png">
                </div> 
                <div class="col-sm-9" style="margin-top:2em;">
                        <div class="informations" >
                            <form>
                                    <table>
                                        <tr>
                                            <td style="width:11em;"> Ime: </td>
                                            <td> <input class="form-control" type="text" v-model="user.name" v-bind:value="name" > {{name}}</td>
                                        </tr>
                                        <tr> 
                                            <td>Prezime: </td>
                                            <td> <input class="form-control" type="text" v-model="user.surname" v-bind:value="surname"> {{surname}}</td>
                                        </tr>
                                        <tr> 
                                            <td> Korisnicko ime:</td>
                                            <td> <input class="form-control" type="text" v-model="user.username" v-bind:value="username">{{username}} </td>
                                        </tr>
                                        <tr> 
                                            <td> Pol:</td>
                                            <td><input class="form-check-input" type="radio" id="exampleRadios2" value="MUSKI"  v-model="user.gender" >
                                                <label class="form-check-label" for="exampleRadios2">
                                                Muski
                                                </label>
                                            </td>
                                            <td><input class="form-check-input" type="radio" id="exampleRadios2" value="ZENSKI"  v-model="user.gender" >
                                                <label class="form-check-label" for="exampleRadios2">
                                                Zenski
                                                </label>
                                             </td>
                                        </tr>
                                        <tr> 
                                            <td>Datum rodjenja: </td>
                                            <td> <input type="date" class="form-control" v-model="user.birthday"> </td>
                                        </tr>
                                        <tr> 
                                            <td> Adresa:</td>
                                            <td> <input class="form-control" type="text" placeholder="ulica" v-model="user.address.street" v-bind:value="user.address.street"> {{street}}</td>
                                           <td> <input class="form-control" type="text" placeholder="broj" style="width:70px !important;" v-model="user.address.number" v-bind:value="user.address.number"></td></tr>  <tr>
                                            <td> </td> <td> <input class="form-control" type="text" placeholder="grad" style="width:px" v-model="user.address.city" v-bind:value="user.address.city"></td>
                                            <td> <input class="form-control" type="text" placeholder="posta" style="width:70px !important;" v-model="user.address.zipCode" v-bind:value="user.address.zipCode" ></td>
                                       </tr>
                                        <tr> 
                                           <button type="button" class="btn btn-danger" style="width: 122px" v-on:click="changePassword"> Promeni sifru </button>
                                        </tr>  </table>
                                        <div v-if="mode" stylep="top-margin:5px;">
                                           <form id="izmena">
                                           <table>
                                            <tr> 
                                                <td> Stara sifra:  </td>
                                                <td> <input class="form-control" type="password"></td> 
                                            </tr> 
                                            <tr> 
                                                <td> Nova sifra:  </td>
                                                <td> <input class="form-control" type="password"></td> 
                                            </tr>
                                            <tr> 
                                                <td> Ponovite unos nove sifre:  </td>
                                                <td> <input class="form-control" type="password"></td> 
                                            </tr>
                                           </table>
                                           </form>
                                       </div>
                                        
                                  

                            </form>
                            <button type="button" style="margin-top: 5px;" class="btn btn-success" v-on:click="changeProfile">Sacuvaj izmene</button>
                        </div>
                    </div>    
            </div>
        
      </div>
                 
</section>
`,
methods:{
    openProfile: function() {
        router.push(`/profil`)
    }, 
    changePassword: function(){
       this.mode=true
    }, 
    changeProfile: function(){
        axios.post("/EliminacioniREST/rest/profile/changeProfile", {
            "username":''+ this.user.username,
            "name":''+ this.user.name, 
            "surname":''+ this.user.surname,  
            "street":''+ this.user.address.street, 
            "number":''+ this.user.address.number, 
            "city":''+ this.user.address.city, 
            "zipCode":''+ this.user.address.zipCode,
            "gender":''+ this.user.gender,
            "birthday":''+ this.user.birthday
        })
        .then( response => {
            router.push(`/profil`)
        })
    }
},
mounted(){
    axios.get("/EliminacioniREST/rest/profile/profileUser")
    .then( response => {
        this.user = response.data
        this.user.birthday = moment(this.user.birthday).format('YYYY-MM-DD')
    })
    .catch(function(error){
        console.log(error)
    })

},
filters: {
    dateFormat: function(value, format){
        var parsed = moment(value);
        return parsed.format(format)
    }
}
});