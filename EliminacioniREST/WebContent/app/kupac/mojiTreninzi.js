Vue.component("kupac-treninzi", {
    data(){
        return{
          
      trainings: [],
      today: new Date(),
      show: false,
      check: false,
      comment: {},
     com: false,
     selected: {}
        }
    },

template: `
<div class="containerInfo">

		 <div class=" tab-pane container active" style="margin-top: 3rem;">
		 
		 
		 
		 
		     <!-- filtriranje -->
                  <table style= " margin-left:-20px;">
                <tr> 

                    <td>  </td> 
                    <td style= "font-size: 16px;"> Filtrirajte treninga po tipu sportskog objekta:  </td> 
                    <td>
                    <div class="dropdown"> <button class="btn btn-secondary dropdown-toggle"  id="dropdownMenuButton" type="button" data-toggle="dropdown" style= "margin-left:10%;" aria-haspopup="true" aria-expanded="false">
                           Tip sportskog objekta
                            </button>
            
                            <span class="dropdown-menu" aria-labelledby="dropdownMenuButton" >
                            <button class="dropdown-item" type="button" v-on:click="filterType('TERETANA')">TERETANA</button>
                            <button class="dropdown-item" type="button" v-on:click="filterType('BAZEN')">BAZEN</button>
                            <button class="dropdown-item" type="button" v-on:click="filterType('PLESNI STUDIO')">PLESNI STUDIO</button>
                            <button class="dropdown-item" type="button" v-on:click="filterType('SPORTSKI CENTAR')">SPORTSKI CENTAR</button>

                            </span>
                       </div>
                            </td> 


                        <td>  </td> 
                        <td>  </td> 
                        <td>  </td> 
                        <td  style= "font-size: 16px; padding-left:20px;"> ili po tipu treninga:  </td> 
                        <td>
                        <div class="dropdown"> 
                        <button class="btn btn-secondary dropdown-toggle" type="button" data-toggle="dropdown"  id="dropdownMenu2" style="margin-left:20%; margin-top:20px;" aria-haspopup="true" aria-expanded="false" >
                            Tip treninga 
                            </button>
            
                            <span class="dropdown-menu" aria-labelledby="dropdownMenu2" >
                            <button class="dropdown-item" type="button" v-on:click="filterTypeTraining('GRUPNI')">GRUPNI</button>
                            <button class="dropdown-item" type="button" v-on:click="filterTypeTraining('PERSONALNI')">PERSONALNI</button>
                            <button class="dropdown-item" type="button" v-on:click="filterTypeTraining('TERETANA')">TERETANA</button>
                                <button class="dropdown-item" type="button" v-on:click="filterTypeTraining('SAUNA')">SAUNA</button>
                                      <button class="dropdown-item" type="button" v-on:click="filterTypeTraining('NEMA')">NEMA</button>
                          </span>
                          </div>
                        </td>
                      <td style="width:50px;"> </td>
        <td><button class="btn btn-secondary" type="button"  v-if="check" v-on:click="reset()">x</button> </td> 
                      
                </tr>

            </table> 
            
            
            <!-- sortiranje --> 
               <table style= " margin-left:-20px; margin-bottom: 3em;">
                <tr> 

                    <td>  </td> 
                    <td style= "font-size: 16px;"> Sortiranje treninga:  </td> 
                    <td> 
                    <div class="dropdown">
                    <button class="btn btn-secondary dropdown-toggle" type="button" data-toggle="dropdown" id="dropdownMenuSort" style= "margin-left:10%;" >
                            Sortiraj
                            </button>
            
                            <span class="dropdown-menu" aria-labelledby="dropdownMenuSort" >
                            <button class="dropdown-item" type="button" v-on:click="sortNameAsc()">Imenu objekta rastuce</button>
                            <button class="dropdown-item" type="button" v-on:click="sortNameDesc()">Imenu objekta opadajuce</button>
                            <button class="dropdown-item" type="button" v-on:click="sortDateAsc()">Datumu rastuce</button>
                            <button class="dropdown-item" type="button" v-on:click="sortDateDesc()">Datumu opadajuce</button>
                 
                            </span>

                            </div>
                        </td> 
      
                </tr>
				
            </table> 
    

        </div>
 
		 
		 
		            
			         
			         	           <!--tabela-->
  <div >
    <table class="table table-hover" style="width: 80%; margin-left: 12%">
        <thead>
          <tr>
            <th scope="col">Datum</th>
            <th scope="col">Naziv treninga</th>
            <th scope="col">Sportski objekat</th>
            <th scope="col">Tip sportskog objekta</th>
            <th scope="col">Tip treninga</th>
            <th> </th> 
              <th> </th> 
          </tr>
        </thead>
        <tbody>
          <tr v-for="t in trainings" >
            <td>{{t.date | dateFormat('DD.MM.YYYY.')}}</td>
            <td>{{t.name}}</td>
             <td>{{t.place}}</td>
               <td>{{t.placeType}}</td>
               <td>{{t.trainingType}}</td>
             <td><button v-if="t.canICancel && !t.isCanceled" v-on:click="otkazi(t.date)"> Otkazi </button> </td>
                <td v-if="t.isCanceled">OTKAZAN</td>
                 <td v-if="t.canICancel" > <button v-on:click="komentarisi(t)"> Komentarisi</button> </td>
            <div>
            
            </div>
          </tr>
        </tbody>
    </table>
  </div>     
		                 
                   <div v-if="com" style="height: 50rem;width: 50rem; margin-left: 30%" > 
		    
		    <label> Ocena: </label>
		    
		     <select class="form-control" v-model="comment.grade"  style="background-color: lightgray">
                                         
                                          <option  v-bind:value="1" style=" background-color:white; color: black">1</option>
                                           <option  v-bind:value="2" style=" background-color:white; color: black">2</option>
                                           <option  v-bind:value="3" style=" background-color:white; color: black" >3</option>
                                           <option  v-bind:value="4" style=" background-color:white; color: black">4</option>
                                           <option  v-bind:value="5" style=" background-color:white; color: black">5</option>
                            </select>
                            
                <label> Komentar </label>   
                
                <textarea class="form-control" v-model="comment.comment" type="text">            </textarea> 
		    <button type="button" v-on:click="commentCreate()"> Komentarisi</button> </td>
		     </div>       
           
		             
		    </div>     
		    
		  
		    
    
</div> 
`,
methods:{
komentarisi(training){
this.selected=training,
console.log(training)
this.com= true;

},
commentCreate:function(){
      let komentar ={}
      komentar.idComment = 0, 
      komentar.idPlace = this.selected.idPlace,
      komentar.comment = this.comment.comment
      komentar.grade = this.comment.grade;
      komentar.deleted=false
      komentar.approved = false
      
      console.log(komentar)
      
      	 axios.post("/EliminacioniREST/rest/comment/createComment", komentar)
      .then( response => {
     	 axios.get("/EliminacioniREST/rest/trainingHistory/getTrainingsByUser")
      .then( response => {
     
          this.trainings = response.data;
          console.log(this.trainings)
      
      })
      .catch(function(error){
          console.log(error)
      });
      
      })
      .catch(function(error){
          console.log(error)
      });
      

},

	  otkazi(tr){
       console.log(tr)
       var datum = (tr).toString()
       console.log(datum)
        axios.post("/EliminacioniREST/rest/trainingHistory/cancelUser2", datum)
      .then( response => {
     
 
      	 axios.get("/EliminacioniREST/rest/trainingHistory/getTrainingsByUser")
      .then( response => {
     
          this.trainings = response.data;
          console.log(this.trainings)
      
      })
      .catch(function(error){
          console.log(error)
      });
      })
      .catch(function(error){
          console.log(error)
      });
       
       },
	
	
	  filterType: function (type){
        this.trainings = this.trainings.filter(user => user.placeType === type);
        
        this.check = true
    },
      filterTypeTraining: function (type){
        this.trainings = this.trainings.filter(user => user.trainingType === type);
        
        this.check = true
    },
    
    reset: function(){
       axios.get("/EliminacioniREST/rest/trainingHistory/getTrainingsByUser")
      .then( response => {
     
          this.trainings = response.data;
          console.log(this.trainings)
      
      })
      .catch(function(error){
          console.log(error)
      });
    
    },
	
    sortNameAsc: function() {
        function compare(a, b) {
          if (a.name < b.name)
            return -1;
          if (a.name > b.name)
            return 1;
          return 0;
        }

        return this.trainings.sort(compare);
    }, 
    sortNameDesc: function() {
        function compare(a, b) {
          if (a.name < b.name)
            return 1;
          if (a.name > b.name)
            return -1;
          return 0;
        }
        
        return this.trainings.sort(compare);
    },
        sortDateAsc: function() {
        function compare(a, b) {
          if (a.name < b.name)
            return -1;
          if (a.name > b.name)
            return 1;
          return 0;
        }

        return this.trainings.sort(compare);
    }, 
    sortDateDesc: function() {
        function compare(a, b) {
          if (a.date < b.date)
            return 1;
          if (a.date > b.date)
            return -1;
          return 0;
        }
        
        return this.trainings.sort(compare);
    },
},
filters: {
    dateFormat: function(value, format){
        var parsed = moment(value);
        return parsed.format(format)
    }
},
mounted(){

   	 axios.get("/EliminacioniREST/rest/trainingHistory/getTrainingsByUser")
      .then( response => {
     
          this.trainings = response.data;
          console.log(this.trainings)
      
      })
      .catch(function(error){
          console.log(error)
      });



}


});