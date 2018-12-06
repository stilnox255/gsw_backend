data = {
  parts: [],
}

var user = new Vue({
  el: '#header',
  data: { user: "" },
  methods: {
    loadUser: function () {
      let ref = new URLSearchParams(window.location.search).get('ref');
      fetch("/wild/resources/user/" + ref)
        .then(r => r.json())
        .then(j => { this.user = j.publicName })
        .catch(e => this.message = e.message);
    }
  }
});


// var categories = new Vue({
//   data: data,
//   methods: {
//     loadList: function () {
//     }
//   }
// });

// var parts = new Vue({
//   el: '#parts',
//   data: data,
//   methods: {
//     loadList: function (categorie) {
//       let ref = new URLSearchParams(window.location.search).get('ref');
//       fetch("/wild/resources/user/" + ref + "/categories/" + categorie + "/parts")
//         .then(r => r.json())
//         .then(j => {
//           this.parts = j;
//           this.parts.forEach(part => {

//             part.isReh = false;
//             part.isWs = false;
//             part.isHoney = false;

//             let name = part.category.parent ? part.category.parent.name : part.category.name;

//             switch (name) {
//               case 'Reh':
//                 part.isReh = true;
//                 break;
//               case 'Wildschwein':
//                 part.isWs = true;
//                 break;
//               case 'Honig':
//                 part.isHoney = true;
//                 break;
//             }

//             part.whatsapp = 'https://wa.me/4917634098326?text=' + encodeURIComponent(part.category.name);
//             part.mail = 'mailto:mail@ingoschindler.de?subject=' + encodeURIComponent(part.category.name) + '&body=' + encodeURIComponent(part.category.name);
//           });
//         })
//         .catch(e => this.message = e.message);
//     }
//   }
// });

// document.addEventListener("readystatechange", function () {
//   if (document.readyState === "complete") {
//     user.loadUser();
//     parts.loadList();
//   }
// });


// window.addEventListener("hashchange", function () {
// });