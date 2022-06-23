Vue.component("admin-profile", {
    data(){
        return{
            admin:{}
        }
    },
template: `
<section> 
<div class="containerInfo"> 
            <div class="row content" >
                    <div class="col-sm-5 sidenav" style="margin-left: 10%">
                        <h3 style="width: 200px;" ><small>Vase informacije na profilu:</small> <hr> </h3>
                        <img class= "img-responsive" src="../pictures/korisnik.png">
                    </div> 
                    <div class="col-sm-9" style="margin-top:2em;">
                            <div class="informations" >
                                
                                <table>
                                <tr>
                                    <td style="width:12em;" > Ime: </td>
                                   
                                    <td> {{admin.name}} </td>
                                    </tr>
                                <tr> 
                                <td>Prezime: </td>
                                
                                <td> {{admin.surname}} </td>
                                </tr>
                                <tr> 
                                <td> Korisnicko ime:</td>
                                
                                <td> {{admin.username}} </td>
                                </tr>
                                <tr> 
                                <td> Pol:</td>
                                
                                <td> {{admin.gender}} </td>
                                </tr>
                                <tr> 
                                <td>Datum rodjenja: </td>
                                
                                <td> {{admin.birthday | dateFormat('DD.MM.YYYY.')}} </td>
                                </tr>
                                <tr> 
                                    <td> Adresa:</td>
                                    
                                    <td> {{admin.address.street}} {{admin.address.number}}, grad {{admin.address.city}}  {{admin.address.zipCode}} </td>
                                </tr>
                            
                              
                                <tr style="height: 10px;"> </tr>
                                </table>
                                <button type="button" class="btn btn-success" v-on:click="editProfile">Izmeni podatke</button>
                            </div>
                    </div>
            </div>
     </div>      
</section>
`,
methods:{
    editProfile: function() {
        router.push(`/izmeniProfil`)
    },

},
mounted(){
    axios.get("/EliminacioniREST/rest/profile/profileUser")
        .then( response => {
            this.admin = response.data;
     
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