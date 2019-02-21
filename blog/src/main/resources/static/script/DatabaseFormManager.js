class DatabaseMan {
    constructor() {
        this.id = "DBM" + uuid();
        this.parent = parent;
        console.log("Database man        : " + this.id);
    }

    fetchInputFields(id) {
        fetch('http://localhost.:8080/category/databaseform', {
                method: 'GET'
            })
            .then(response => Promise.all([response, response.json()]))
            .then(([response, json]) => {
                if (!response.ok) {
                    throw new Error(json.message);
                }
                console.log(json);
                let fields = this.inputFieldMapper(json);
                //console.log(fields)
                let div = document.querySelector('#'+id);
                //console.log(div.firstElementChild);
                console.log("Injecting fields in DB form.");
                div.firstElementChild.innerHTML += fields;
            });
            // .catch(exception => {
            //     var errorMap = new Map([
            //         [TypeError, "There was a problem fetching the response."],
            //         [SyntaxError, "There was a problem parsing the response."],
            //         [Error, exception.message]
            //     ]).get(exception.constructor);
            // });
    }

    inputFieldMapper(json) {
        //console.log(json);
        return Object.keys(json).map(input => {
            //console.log(input);
            //console.log(json[input]);
            if (json[input].text) {
                return `<input id="FLD${input}" type="text" class="form-control mt-1 mb-1"  placeholder="${json[input].text}">`;
            } else if (json[input].Select) {
                return `<select id="FLD${input}" class="form-control mt-1 mb-1">
                            ${json[input].Select.map((options, index) => {
                                return (index == 0) ? `<option value="${options}" selected disabled>${options}</option>` : `<option value="${options}">${options}</option>`;
                            }).join("")}
                        </select>`;
            } else if(json[input].divider){
                return `
                    <div class="col-sm-auto text-center mt-2">${json[input].divider}</div>`;
            }
        }).join("");
    }

}