Vue.component("edit-training", {
    data(){
        return{
            place: {},
            training: {},
            image: "",
            id: ""
      	
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
						<label> Trajanje:</label>
					 </div>
					<div class="col-sm-5"> 
						  <input class="form-control" type="number" v-model="training.duration" />
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
                              <input type="file" onchange="encodeImageFileAsURL(this)" v-model="image">
                            </div>
                      </div>
                      
               <div class="row"> <button class="btn btn-success" type="button" style="width: 8rem; margin-left: 45rem; margin-bottom: 3rem;" v-on:click="editTraining()"> Sacuvaj </button> </div>        
	<!--kraj col-sm-15 -->
		 </div> 
	 </div> 
	         
		             
</div>   
`,
methods:{
	editTraining: function(){
	if(this.image != ""){
		this.training.image= image
		}
	  axios.post("/EliminacioniREST/rest/training/editTraining", {
	  "idTraining": + this.training.idTraining,
      "name":''+ this.training.name,
      "type": this.training.type, 
       "idPlace": this.id,
       "duration": this.training.duration, 
       "usernameCoach":''+ this.training.usernameCoach, 
       "description":''+ this.training.description,
		"image":''+ this.training.image,  
		"deleted" :  this.training.deleted,
     
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
	getTraining: function(id){
	  axios.post("/EliminacioniREST/rest/training/getTraining", id)
      .then(
        response => {
          this.training = response.data
        } 
      )
      .catch(function(error){
        
    })
	}
	
       
},
mounted(){
   this.id= this.$route.query.id,
   console.log(this.id)
	this.getTraining(this.id)
	}
  
})