Vue.component("kupac-treninzi", {
    data(){
        return{
          
      trainings: []
        }
    },

template: `
<div class="containerInfo">

		 <div class=" tab-pane container active" style="margin-top: 3rem;">
		            
			         
			         	           <!--tabela-->
  <div >
    <table class="table table-hover" style="width: 80%; margin-left: 12%">
        <thead>
          <tr>
            <th scope="col">Datum</th>
            <th scope="col">Naziv treninga</th>
            <th scope="col">Sportski objekat</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="t in trainings" >
            <td>{{t.date | dateFormat('DD.MM.YYYY.')}}</td>
            <td>{{t.name}}</td>
             <td>{{t.place}}</td>
            <div>
            
            </div>
          </tr>
        </tbody>
    </table>
  </div>     
		                 
                    
           
		             
		    </div>          
</div> 
`,
methods:{



	

	
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
      
      })
      .catch(function(error){
          console.log(error)
      });



}


});