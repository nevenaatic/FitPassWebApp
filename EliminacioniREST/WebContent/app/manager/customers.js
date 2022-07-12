Vue.component("allUsers-manager", {
    data(){
        return{
            place:{}, 
            users: []
      
        }
    },
template: `
<div class="containerInfo">

	             <!--tabela-->
  <div id="users">
    <table class="table table-hover" style="width: 70%; margin-left: 14%">
        <thead>
          <tr>
            <th scope="col">Ime</th>
            <th scope="col">Prezime</th>
            <th scope="col">Korisnicko ime</th>
   
            <th scope="col"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="user in users" >
            <td>{{user.name}}</td>
            <td>{{user.surname}}</td>
            <td>{{user.username}}</td>
       
            <div>
            
            </div>
          </tr>
        </tbody>
    </table>
  </div>
		             
</div>   
`,
methods:{

getCoaches: function(id){
  axios.post("/EliminacioniREST/rest/training/getUsersForPlace", id)
      .then( response => {
        
       this.users = response.data
    
   
      
      })
      .catch(function(error){
          console.log(error)
      });
}
       
},
mounted(){
        axios.get("/EliminacioniREST/rest/place/myPlace")
      .then( response => {
        
       this.place = response.data,
       this.id= this.place.id,
       console.log(this.id)
       this.getCoaches(this.id);
      
      })
      .catch(function(error){
          console.log(error)
      });
   
   
}
})