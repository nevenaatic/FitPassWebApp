Vue.component("kupac-treninzi", {
    data(){
        return{
          
      traiings: []
        }
    },

template: `
<div class="containerInfo">

		 <div class=" tab-pane container active" style="margin-top: 3rem;">
		            
			         treninzi
	
           
		             
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

      



}


});