class DatabaseMan {
  constructor(parent) {
    this.id = "DBM" + uuid();
    this.parent = parent;
    this.fieldList = [];
    console.log("Database man        : " + this.id);
  }
  getParent() {
    return this.parent;
  }
  fetchInputFields(id) {
    fetch("http://localhost.:8080/category/databaseform", {
      method: "GET"
    })
      .then(response => Promise.all([response, response.json()]))
      .then(([response, json]) => {
        if (!response.ok) {
          throw new Error(json.message);
        }
        console.log(json);
        let fields = this.inputFieldMapper(json);
        //console.log(fields)
        let div = document.querySelector("#" + id);
        //console.log(div.firstElementChild);
        console.log("Injecting fields in DB form.");
        div.firstElementChild.innerHTML += fields;
      })
      .catch(exception => {
        var errorMap = new Map([
          [TypeError, "There was a problem fetching the response."],
          [SyntaxError, "There was a problem parsing the response."],
          [Error, exception.message]
        ]).get(exception.constructor);
      });
  }
  inputFieldMapper(json) {
    //console.log(json);
    return Object.keys(json)
      .map(input => {
        //console.log(input);
        //console.log(json[input]);
        if (json[input].text) {
          this.fieldList.push({
            id: `FLD${input}`,
            type: "text",
            rules: json[input].rules,
            field: json[input].field
          });
          return `<input 
                            id="FLD${input}" 
                            type="text" 
                            class="form-control mt-1 mb-1"  
                            placeholder="${json[input].text}">`;
        } else if (json[input].Select) {
          this.fieldList.push({
            id: `FLD${input}`,
            type: "select",
            rules: json[input].rules,
            field: json[input].field
          });
          return `<select id="FLD${input}" class="form-control mt-1 mb-1">
                            ${json[input].Select.map((options, index) => {
                              return index == 0
                                ? `<option value="${options}" selected disabled>${options}</option>`
                                : `<option value="${options}">${options}</option>`;
                            }).join("")}
                        </select>`;
        } else if (json[input].divider) {
          return `
                    <div class="col-sm-auto text-center mt-2">${
                      json[input].divider
                    }</div>`;
        }
      })
      .join("");
  }
  inputFieldValidator() {
    console.log(this.fieldList);
    console.log("inside the validator");
    let pass = true;

    //fields
    for (let index = 0; index < this.fieldList.length; index++) {
      //console.log(this.fieldList[index].type);

      if (this.fieldList[index].type == "text") {
        let elemId = this.fieldList[index].id;
        console.log(
          "===================Input Id: " +
            elemId +
            "============================="
        );
        let inputElem = document.querySelector("#" + elemId);
        //console.log(inputElem);

        let nullable = true;

        //rules
        for (
          let ruleIndex = 0;
          ruleIndex < this.fieldList[index].rules.length;
          ruleIndex++
        ) {
          let rules = this.fieldList[index].rules[ruleIndex];
          //console.log("-"+rules);

          switch (rules) {
            case "notnull":
              console.log("Rule: " + rules);
              nullable = false;
              console.log("-Nullable: " + nullable);
              break;
            case "alpha":
              console.log("Rule: " + rules);
              break;
            case "alphanum":
              console.log("Rule: " + rules);
              break;
            case "num":
              console.log("Rule: " + rules);
              break;
            default:
              console.log("Rule(default): " + rules);
          }

          if (rules.includes("char")) {
            let ruleCount = rules.split("char")[0];
            console.log("-Rules Char Count Max: " + ruleCount);

            let charCount = inputElem.value.length;
            console.log("-Elem Char Count: " + charCount);

            console.log("-Elem Value Nullable: " + nullable);

            if (charCount == 0 && !nullable) {
              pass = false;
              inputElem.placeholder += inputElem.placeholder.includes(
                " | Can Not Be Empty!"
              )
                ? ""
                : " | Can Not Be Empty!";
              inputElem.classList.add("is-invalid");
              console.error("Field Empty");
            } else if (charCount > ruleCount && !nullable) {
              pass = false;
              inputElem.value = "";
              inputElem.placeholder = `${
                this.fieldList[index].type
              } | ${charCount}/${ruleCount}, Too Many Chars!`;
              inputElem.classList.add("is-invalid");
              console.error("Too Many Chars");
            } else if (inputElem.classList.contains("is-invalid")) {
              inputElem.classList.remove("is-invalid");
            }
          }
        }

        nullable = true;

        if (pass) {
          console.log("Pass: " + pass);
        }
      } else if (this.fieldList[index].type == "select") {
        let elemId = this.fieldList[index].id;
        console.log(
          "===================Select Id: " +
            elemId +
            "============================="
        );
        let inputElem = document.querySelector("#" + elemId);
        let selected = inputElem.options[inputElem.selectedIndex].value;
        console.log(selected);

        if (selected.includes("Select")) {
          pass = false;
          inputElem.classList.add("is-invalid");
          console.error("Must Make A selection");
        } else if (inputElem.classList.contains("is-invalid")) {
          inputElem.classList.remove("is-invalid");
        }
      }
    }

    return pass;
  }
  buildDict() {
    let file = document.querySelector("#FLD0").files[0];
    getBase64(file)
      .then(data => {
        let dict = {};
        dict["img"] = "data";
        this.buildDictFields(dict);
      })
      .catch(error => {

        console.log(error);
        this.getParent().notify("Must Upload An Image Of The Laptop!")
      
      });
  }
  buildDictFields(dict) {
    //let dict = {};
    let pass = this.inputFieldValidator();
    if (!pass) {
      for (let index = 0; index < this.fieldList.length; index++) {
        let id = this.fieldList[index].id;
        let field = this.fieldList[index].field;
        let input = document.querySelector("#" + id);

        console.log(this.fieldList[index].type);

        switch (this.fieldList[index].type) {
          case "text":
            dict[field] = input.value;
            break;
          case "select":
            dict[field] = input.options[input.selectedIndex].value;
            break;
        }
      }
    }
    console.log(dict);
    this.sendBuiltDict(dict);
  }
  sendBuiltDict(dict){
    fetch('http://localhost.:8080/database/add',{
        method: "POST",
        headers:{
            "email":"",
            "token":""
        },
        body:JSON.stringify(dict)
    })
    .then(response => console.log(response))
    .catch(error => console.log(error));
  }
}
