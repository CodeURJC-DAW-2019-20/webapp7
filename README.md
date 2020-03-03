<p align="center"><img src="https://i.ibb.co/ZgJ26q7/logo.png"></p>

<p align="center">
<img alt="Hex.pm" src="https://img.shields.io/badge/DAW-7-orange">
<img alt="Hex.pm" src="https://img.shields.io/badge/Members-5-blue">
<img alt="Hex.pm" src="https://img.shields.io/hexpm/l/plug?color=red">
</p>


# VoluntaWeb - Group 7 
VoluntaWeb is a project developed by group 7 of the subject "Web Application Development". It consists of an online site where you can publish your volunteer ad or search for NGOs to join them. You will have a panel from where you can manage your ads (if you register as an NGO) or consult which NGOs you are helping.

## Members
| Name | Mail address | GitHub username|
|--------|--------|------------|
|Alicia Merino Martínez| a.merinom.2017@alumnos.urjc.es| aliholi44 |
|Daniel Fuente Martínez| d.fuente.2017@alumnos.urjc.es | dfuente2017 |
|Daniel Serrano Cobos| d.serranoc.2017@alumnos.urjc.es | theroxd4n |
|Pablo Atahonero García de Blas| p.atahonero.2017@alumnos.urjc.es | PabloAtahoneroGB |
|Samuel Severiche Berna | s.severiche.2017@alumnos.urjc.es | sbsam |

  
## Our links
[Trello](https://trello.com/b/nNVdsRsp)

## Main aspects
### Entities
VoluntaWeb has 4 entities: users (users table), support comments (comments table), NGOs(ngos table) and volunteerings (volunteerings table).  
-NGOs can publish volunteerings ads (ngo_volunteering table).  
-Users can join volunteerings (user_volunteering).  
### Permissions
-Visitors can search NGOs and volunteerings but not join them or like them.  
-Logged users can do the actions vistors can do, but also join volunteerings and like them and modify their user settings.  
-NGOs can publish volunteerings and edit/remove them, aswell edit their NGO settings.  
-Administrator can moderate volunteerings.  
### Images
-Logged user have a profile image.  
-NGOs have a profile image.  
-Volunteerings have a banner image.  
### Charts
We're implemented graphic charts:  
-Volunteerings published in a month.  
-Users joined for a volunteering in a month.  
-Users registered in a month.  
### Complementary technonlogies
-We are consuming the Google Maps API for include location map in a volunteering.
### Advanced algorithm
-The search page has a search filter for volunteerings and NGOs.
### Diagrams
#### Navigation diagram
<img src="./diagrams/navDiagram.svg">

#### Database diagram
<img src="./diagrams/DatabaseDiagram.svg"> 

#### Class diagram
<img src="./diagrams/UpdatedClassDiagram.svg"> 

### Screenshots
![](./screenshots/F2-index.PNG)
#### Description
"Index" page is the main page of the web application. It's the entry point to the other pages.

![](./screenshots/F2-aboututs.PNG)
#### Description
"About Us" is a page that have some information about VoluntaWeb and it's objectives.  

![](./screenshots/F2-contact.PNG)
#### Description
The "Contact" page, as her name says, is for contact the administrator of the website in order to make sugerences and questions.  

![](./screenshots/F2-volunteerings.PNG)
#### Description
"Volunteering-gestion-panel" is a page that have, mainly, two options: edit and delete. Also appears the last modification.

![](./screenshots/F2-ongs.PNG)
#### Description
"Ongs" is a page that contains all ONGs that are in our data base. It is possible to filter a search looking ONG's name.

![](./screenshots/F2-login.PNG)
#### Description
"Login" is a page where users, such as volunteers and ONGs, can login and browse the website.

![](./screenshots/F2-my-volunteerings.PNG)
#### Description
This webpage shows all the volunteers to whom a user of the web has subscribed, with their title, their location, the email of the corresponding NGO and more information about the activity by clicking on it.
 
![](./screenshots/F2-ong-id.PNG)
#### Description
This webpage shows information about a specific NGO, with its name, its data and also a short description. Also below shows recent volunteers from that NGO.

![](./screenshots/F2-ong-settings.PNG)
#### Description
This website allows NGOs to change data on their Voluntaweb account such as their name, address, email, category, profile picture etc.
  
![](./screenshots/F2-register.PNG)
#### Description
This webpage allows users to chose if they want register as NGOs or Volunteers.

![](./screenshots/F2-register-ong.PNG)
#### Description
This webpage allows NGOs to create an account in VoluntaWeb.

![](./screenshots/F2-register-user.PNG)
#### Description
This webpage allows Volunteers to create an account in VoluntaWeb.

![](./screenshots/F2-search.PNG)
#### Description
“Searches” This page display the results of a search and allow the user to change the parameters of the search.

![](./screenshots/F2-ong-submit-advertisement.PNG)
#### Description
“Ong submit advertisement” This page will allow the NGOs to submit a volunteering offer.

![](./screenshots/F2-user-settings.PNG)
#### Description
“User settings” This page will allow the user to change the settings of his account.

![](./screenshots/F2-voluntariado-detail.PNG)
#### Description
 “Volunteering” This page is an example of the design of the volunteering offers.


## Development environment setup:
For the development of this application we have used Spring in its latest version. Specifically, we have used the Spring Tools 4 development. This tool is available for different IDEs (Eclipse, visual studio code, Theia).
* Install Java SE Development Kit 13
* Instal Eclipse IDE
* Install Sprint Tools 4 from the Eclipse Marketplace.
* Import the project: File> Import> Maven> Existing Maven Projects> Browse directory > Select folder of the project and finish.

## Commits list
Samuel Severiche (sbsam):
  * Develop: ONG and ONG detail pages - https://github.com/CodeURJC-DAW-2019-20/webapp7/commit/d03b6925d0180e26073c256db742148e9630ca52
  * Develop: Register ong with images - https://github.com/CodeURJC-DAW-2019-20/webapp7/commit/9dc0e565dc08f194a5fb35668cc43c18049379f9
  * Develop: User and ONG settings - https://github.com/CodeURJC-DAW-2019-20/webapp7/commit/096279ec03e3b45181bf7cf30eff13238b3ad365
  * Develop: Admin pages and minor fixes (admin pages: ngos, volunteerings, users, comments and delete functionality). - https://github.com/CodeURJC-DAW-2019-20/webapp7/commit/68c4c9f041207f7693c6de17da0f071b596d4f1c
  * Develop: admin comments section and aboutus navbar - https://github.com/CodeURJC-DAW-2019-20/webapp7/commit/0b04bed303adcda43580a4189fe8d4205f7abbc4

Alicia Merino (aliholi44):
  * Develop: Volunteering's View - https://github.com/CodeURJC-DAW-2019-20/webapp7/commit/645c8c4ed416fc3ad7adcdf7f717d4bc3ff165d8
  * Develop: Join button - https://github.com/CodeURJC-DAW-2019-20/webapp7/commit/bd4371dafed99d1d00701c9d9616fca13c7acc64
  * Develop: deleteCount and delete an account - https://github.com/CodeURJC-DAW-2019-20/webapp7/commit/db5bd38de804813d0e016b84129d07dec338c7ee
  * Develop: fixed some pages - https://github.com/CodeURJC-DAW-2019-20/webapp7/commit/896e6d77e04293f2478c1beb34cf3685d499134c
  * Develop: aboutUs page - https://github.com/CodeURJC-DAW-2019-20/webapp7/commit/cbf9fd1af161a578851e299316cc33a1d159f52b
  
Pablo Atahonero (PabloAtahoneroGB)
  * 27/02 - Develop: Like any volunteering - https://github.com/CodeURJC-DAW-2019-20/webapp7/commit/4ce8a55ea9e4b14da6e2c33d2861688bad108eeb
  * 27/02 - Develop: Delete a like from a volunteering - https://github.com/CodeURJC-DAW-2019-20/webapp7/commit/4ce8a55ea9e4b14da6e2c33d2861688bad108eeb
  * 27/02 - Develop: Knowing if i liked a volunteering by the style of like button - https://github.com/CodeURJC-DAW-2019-20/webapp7/commit/4ce8a55ea9e4b14da6e2c33d2861688bad108eeb
  * 27/03 - Develop: Number of likes in total of the volunteerings - https://github.com/CodeURJC-DAW-2019-20/webapp7/commit/4ce8a55ea9e4b14da6e2c33d2861688bad108eeb
  * 29/02 - Develop: Page my volunteerings - https://github.com/CodeURJC-DAW-2019-20/webapp7/commit/76362269727bb112cff14df6f5cfd16f3418bef7

Daniel Fuente (dfuente2017)
  * Creadion, edicion y borrado de anuncios y edición de ong funcionando - https://github.com/CodeURJC-DAW-2019-20/webapp7/commit/33a3d94cd3ca9662ee2e71835ef7b4c3d179ae6c
  * Implementado subida de imágenes y consumo en la lista de voluntariado… - https://github.com/CodeURJC-DAW-2019-20/webapp7/commit/e82f5e1c1b204327b342771d2f9916c18d7b738c
  * Rama lista para implementar el usuario. - https://github.com/CodeURJC-DAW-2019-20/webapp7/commit/fa0b71e60828bce18d3294e81b0b0970e18dae90
  * Cambiado category a como esta en develop. - https://github.com/CodeURJC-DAW-2019-20/webapp7/commit/f95f745c45ac44a289edae1ca883fc0cb551a4e6
  * Actualizados navbar de la parte de ongs -https://github.com/CodeURJC-DAW-2019-20/webapp7/commit/fb34ded98c95e401dd7adf6373c892f4b697fd76
  
  
  Daniel Serrano Cobos (theroxd4n)
  * fixed join volunteerings - https://github.com/CodeURJC-DAW-2019-20/webapp7/commit/d84cfb6285188fd243d8c913b13af9e36f21692e
  * Merge pull request #5 from CodeURJC-DAW-2019-20/feature/charts - https://github.com/CodeURJC-DAW-2019-20/webapp7/commit/099c943f340b0c15f5e7fea96c526e725c31a919
  * Merge pull request #4 from CodeURJC-DAW-2019-20/feature/search - https://github.com/CodeURJC-DAW-2019-20/webapp7/commit/6473753ca43fac44c5df06df7935bb919624ce2c
  * The Great Commit - https://github.com/CodeURJC-DAW-2019-20/webapp7/commit/3e19774a18789f96d0c22ce31b3a3d4f7b8ad89d
  * Merge pull request #17 from CodeURJC-DAW-2019-20/feature/comments - https://github.com/CodeURJC-DAW-2019-20/webapp7/commit/49a82bdbaecf6782659d0f0ad24e11db15ff601c
  * login bug - https://github.com/CodeURJC-DAW-2019-20/webapp7/commit/03dd7f6b6d526e9549ee44a650b73ca0d7741835
