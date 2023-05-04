<!-- Improved compatibility of back to top link: See: https://github.com/othneildrew/Best-README-Template/pull/73 -->
<a name="readme-top"></a>
<!--
*** Thanks for checking out the Best-README-Template. If you have a suggestion
*** that would make this better, please fork the repo and create a pull request
*** or simply open an issue with the tag "enhancement".
*** Don't forget to give the project a star!
*** Thanks again! Now go create something AMAZING! :D
-->



<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links
-->
[![MIT License][license-shield]][license-url]
[![LinkedIn][linkedin-shield]][linkedin-url]



<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/othneildrew/Best-README-Template">
    <!--<img src="images/logo.png" alt="Logo" width="80" height="80">-->
  </a>

<h3 align="center">Selenium Testing - Amazon.com</h3>

  <p align="center">
    A sample project of automated tests built with Java, Selenium, and other tools for the Amazon.com website. 
    <br />
  </p>
</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
   <ol>
     <li><a href="#about-the-project">About The Project</a>
       <ul>
         <li><a href="#built-with">Built With</a></li>
       </ul>
     </li>
     <li><a href="#getting-started">Getting Started</a>
       <ul>
         <li><a href="#prerequisites">Prerequisites</a></li>
         <li><a href="#usage">Usage</a></li>
       </ul>
     </li>
     <li><a href="#test-cases">Test Cases</a></li>
     <li><a href="#license">License</a></li>
     <li><a href="#contact">Contact</a></li>
   </ol>
</details>

<!-- ABOUT THE PROJECT -->
## About The Project

### Built With

| Tool                                                 | Description                                                                                                                                                |
|------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [![Maven][Maven]][Maven-url]                         | Apache Maven is a software project management and comprehension tool.                                                                                      |
| [![Selenium][Selenium]][Selenium-url]                | Selenium supports automation of all the major browsers in the market through the use of WebDriver.                                                         |
| [![TestNG][TestNG]][TestNG-url]                      | TestNG is a testing framework designed to simplify a broad range of testing needs, from unit testing to integration testing.                               |
| [![GithubActions][GithubActions]][GithubActions-url] | GitHub Actions is a continuous integration and continuous delivery (CI/CD) platform that allows you to automate your build, test, and deployment pipeline. |
| [![ExtentReports][ExtentReports]][ExtentReports-url] | With the ExtentReports library, you can create beautiful, interactive and detailed reports for your tests.                                                 |

<p align="right">(<a href="#readme-top">back to top</a>)</p>


<!-- GETTING STARTED -->
## Getting Started

This is an example of how you may give instructions on setting up your project locally.
To get a local copy up and running follow these simple example steps.

### Prerequisites

* Java 8 or later
* Maven
* Google Chrome

### Usage

1. Clone the repo
   ```sh
   git clone https://github.com/gera-cl/selenium-testing-amazon
   ```
2. Run tests using Maven
   ```sh
   mvn test
   ```

<p align="right">(<a href="#readme-top">back to top</a>)</p>


## Test Cases

<table>
     <tr>
         <th>Test Title</th>
         <td>Check Language Switch</td>
     </tr>
     <tr>
         <th>Test ID</th>
         <td>T001</td>
     </tr>
     <tr>
         <th>Description</th>
         <td>Verify if the language switch function is working correctly</td>
     </tr>
     <tr>
         <th>Preconditions</th>
         <td>The user has access to the Amazon.com website</td>
     </tr>
     <tr>
         <th>Steps</th>
         <td>1. Go to the Amazon.com website
            <br>2. Locate the language switch dropdown in the top right corner of the page
            <br>3. Click on the dropdown and select a language different from the default language
            <br>4. Verify that the language of the website has changed to the selected language
         </td>
     </tr>
     <tr>
         <th>Expected Result</th>
         <td>The language of the website changes to the selected language and all elements of the page are displayed correctly in the selected language</td>
     </tr>
</table>
<table>
     <tr>
         <th>Test Title</th>
         <td>Check Address Changing using US Zip Code</td>
     </tr>
     <tr>
         <th>Test ID</th>
         <td>T002</td>
     </tr>
     <tr>
         <th>Description</th>
         <td>Verify if the user can change the delivery address using a US zip code</td>
     </tr>
     <tr>
         <th>Preconditions</th>
         <td>The user is logged in and has access to the Amazon.com website</td>
     </tr>
     <tr>
         <th>Steps</th>
         <td>1. Go to the Amazon.com website
            <br>2. Click on the "Deliver to" section found in the top bar
            <br>3. Enter a valid US zip code in the "Location" section
            <br>4. Click on the "Apply" button
            <br>5. Verify that the delivery address has changed to a valid address for the entered zip code
         </td>
     </tr>
     <tr>
         <th>Expected Result</th>
         <td>The delivery address changes to a valid address for the entered zip code and is displayed correctly on the page</td>
     </tr>
</table>
<table>
  <tr>
    <th>Test Title</th>
    <td>Product search using the top bar</td>
  </tr>
  <tr>
      <th>Test ID</th>
      <td>T003</td>
  </tr>
  <tr>
    <th>Description</th>
    <td>Verify that the product search feature is functioning properly by searching for a product using the top bar.</td>
  </tr>
  <tr>
    <th>Steps</th>
    <td>1. Navigate to the Amazon.com home page.
        <br>2. Locate the search bar at the top of the page.
        <br>3. Type in the name or keyword of the product you want to search for.
        <br>4. Click on the magnifying glass icon or press the Enter key.
        <br>5. Check that the search results page is displayed, showing a list of relevant products matching the search criteria.
    </td>
  </tr>
  <tr>
    <th>Expected Result</th>
    <td>The search feature should work properly and display a list of relevant products matching the search criteria.</td>
  </tr>
</table>
<table>
  <tr>
    <th>Test Title</th>
    <td>Check product detail after clicking on PLP</td>
  </tr>
  <tr>
      <th>Test ID</th>
      <td>T004</td>
  </tr>
  <tr>
    <th>Description</th>
    <td>Verify that the product details page is displayed after clicking on a product from the product list page.</td>
  </tr>
  <tr>
    <th>Steps</th>
    <td>1. Navigate to the Amazon.com home page.
        <br>2. Locate the product list page (PLP) by either using the search bar or navigating to a category page.
        <br>3. Click on a product from the list.
        <br>4. Check that the product details page (PDP) is displayed, showing the product's title, description, images, price, and other relevant information.
    </td>
  </tr>
  <tr>
    <th>Expected Result</th>
    <td>The product details page should be displayed and show all relevant information about the product, such as its title, description, images, and price.</td>
  </tr>
</table>
<table>
  <tr>
    <th>Test Title</th>
    <td>Check Product Variations in PDP</td>
  </tr>
  <tr>
      <th>Test ID</th>
      <td>T005</td>
  </tr>
  <tr>
    <th>Description</th>
    <td>Verify that users can select product variations in the Product Detail Page (PDP).</td>
  </tr>
  <tr>
    <th>Steps</th>
    <td>1. Navigate to the PDP of a product that has variations (e.g. different sizes or colors). 
        <br>2. Locate the variation selector (e.g. dropdown menu or color swatches).
        <br>3. Click on the variation selector and select a variation.
        <br>4. Verify that the product image and details have been updated to reflect the selected variation.
        <br>5. Repeat steps 3 and 4 for each available variation, if applicable.
    </td>
  </tr>
  <tr>
    <th>Expected Result</th>
    <td>The selected variation should be reflected in the product image and details. All available variations should be selectable and should update the product image and details accordingly.</td>
  </tr>
</table>
<table>
  <tr>
    <th>Test Title</th>
    <td>Check Product Cart after Product Adding</td>
  </tr>
  <tr>
      <th>Test ID</th>
      <td>T006</td>
  </tr>
  <tr>
    <th>Description</th>
    <td>Verify that the product is added to the cart after the user clicks the "Add to Cart" button.</td>
  </tr>
  <tr>
    <th>Steps</th>
    <td>1. Navigate to the PDP of a product.
        <br>2. Click the "Add to Cart" button.
        <br>3. Verify that a message is displayed indicating that the product has been added to the cart.
        <br>4. Click the cart icon in the top right corner of the page.
        <br>5. Verify that the product appears in the cart.
    </td>
  </tr>
  <tr>
    <th>Expected Result</th>
    <td>The message confirming the product was added to the cart should be displayed, and the product should appear in the cart.</td>
  </tr>
</table>


<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE` for more information.

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- CONTACT -->
## Contact

Germán Hernández - [ghernandez94](https://linkedin.com/in/ghernandez94)

Project Link: [https://github.com/gera-cl/selenium-testing-amazon](https://github.com/gera-cl/selenium-testing-amazon)

<p align="right">(<a href="#readme-top">back to top</a>)</p>


<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[license-shield]: https://img.shields.io/github/license/othneildrew/Best-README-Template.svg?style=for-the-badge
[license-url]: https://github.com/gera-cl/selenium-testing-amazon/blob/master/LICENSE
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/ghernandez94
[product-screenshot]: images/screenshot.png

[Selenium]: https://img.shields.io/static/v1?label=&message=Selenium&logo=selenium&style=for-the-badge&color=37A51F&logoColor=white
[Selenium-url]: https://selenium.dev/
[Maven]: https://img.shields.io/static/v1?label=&message=apache%20maven&logo=apachemaven&style=for-the-badge&color=c4091e
[Maven-url]: https://maven.apache.org/
[TestNG]: https://img.shields.io/static/v1?label=&message=testng&style=for-the-badge&color=feb308
[TestNG-url]: https://testng.org/
[GithubActions]: https://img.shields.io/badge/GitHub_Actions-2088FF?style=for-the-badge&logo=github-actions&logoColor=white
[GithubActions-url]: https://github.com/features/actions
[ExtentReports]: https://img.shields.io/static/v1?label=&message=extent%20reports&style=for-the-badge&color=4a90e2
[ExtentReports-url]: https://extentreports.com
