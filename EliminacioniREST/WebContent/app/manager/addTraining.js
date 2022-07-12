Vue.component("add-training", {
    data(){
        return{
            place: {},
            training: {},
            coaches: [],
            placeId : 0,
            show: true
      
        }
    },
template: `

<div class="containerInfo">

    <div class="row content rows" >
	    <div class="col-sm-7" style="margin-left: 30%">
			    <div class="row" style="height: 5rem;"> </div>
			    
			   <div class="row rows"> 
					 <div class="col-sm-2"> 
						<label> Naziv:</label>
					 </div>
					<div class="col-sm-5"> 
						  <input class="form-control" type="text" v-model="training.name" placeholder="Naziv treninga"/>
					 </div>
			   </div>
			   
			      <div class="row rows"> 
					 <div class="col-sm-2"> 
						<label> Tip:</label>
					 </div>
					<div class="col-sm-2"> 
						  <select class="form-control" v-model="training.type"  style="background-color: lightgray">
                                          <option  v-bind:value="0" style=" background-color:white; color: black">GRUPNII</option>
                                          <option  v-bind:value="1" style=" background-color:white; color: black">PERSONALNI</option>
                                           <option  v-bind:value="2" style=" background-color:white; color: black">TERETANA</option>
                                           <option  v-bind:value="3" style=" background-color:white; color: black" >SAUNA</option>
                                           <option  v-bind:value="4" style=" background-color:white; color: black">NEMA</option>
                            </select>
					 </div>
					 <div class="col-sm-1" > <label style="margin-top: 1rem;"> Trener:</label>  </div>
					  <div class="col-sm-2" > 
					  
                                <select class="form-control" v-model="training.coachUsername" style="background-color: lightgray">
                                <option  style=" background-color:white; color: black" >Nema trenera  </option>
                                    <option  v-bind:value="m.username" style=" background-color:white; color: black" v-for="m in coaches">{{m.name}} {{m.surname}}  </option>
                                  
                                </select>
                              </div>
					 
			   </div>
			   
				 <div class="row rows"> 
					 <div class="col-sm-2"> 
						<label> Trajanje:</label>
					 </div>
					<div class="col-sm-2"> 
						  <input class="form-control" type="number" v-model="training.duration" />
					 </div>
					  <div class="col-sm-1"> 
						<label> Cena:</label>
					 </div>
					 	<div class="col-sm-2"> 
						  <input class="form-control" type="number" v-model="training.price" />
					 </div>
			   </div>
			   
			   	 <div class="row rows"> 
					 <div class="col-sm-2"> 
						<label> Opis:</label>
					 </div>
					<div class="col-sm-5"> 
						  <textarea class="form-control" type="text" v-model="training.description" placeholder="Opis treninga"/>
					 </div>
			   </div>
			   
			   <div class="row rows">
                            <div class="col-sm-2"> 
                              <label> Slika: </label>
                              </div>
                            <div class="col-sm-5"> 
                              <input type="file" onchange="encodeImageFileAsURL(this)" v-model="training.image">
                            </div>
                      </div>
                      
               <div class="row"> <button class="btn btn-success" type="button" style="width: 8rem; margin-left: 45rem; margin-bottom: 3rem;" v-on:click="saveTraining()"> Sacuvaj </button> </div>        
	<!--kraj col-sm-15 -->
		 </div> 
	 </div> 
	         
</div>   	              
 
`,
methods:{

	getCoaches: function(){
	     axios.get("/EliminacioniREST/rest/user/coach")
      .then(
        response => {
          this.coaches = response.data;
        } 
      )
      .catch(function(error){
        alert(error)
    })
	},

       saveTraining: function(){
       if(this.training.type == 3 || this.training.type == 4 ){
       this.training.coachUsername == ""
       }
       if(this.training.image==undefined){
       this.training.image="C:\\fakepath\empty.jpg"
       }
       if(this.training.coachUsername==undefined){
       this.training.coachUsername == ""
       }
        axios.post("/EliminacioniREST/rest/training/createTraining", {
      "name":''+ this.training.name,
      "type":''+ this.training.type, 
      "image":''+ this.training.image,  
      "description":''+ this.training.description, 
      "duration":''+ this.training.duration, 
      "coachUsername":''+ this.training.coachUsername, 
      "idPlace":''+ this.placeId,
      "price" : this.training.price
     })
      .then(
        response => {
          router.push(`/`);
        } 
      )
      .catch(function(error){
        alert(error)
    })
       },
       changeView:function(){
       this.show = false
       }
},
mounted(){
   this.placeId = this.$route.query.id,
   console.log(this.placeId),
   this.getCoaches()

       
}
})