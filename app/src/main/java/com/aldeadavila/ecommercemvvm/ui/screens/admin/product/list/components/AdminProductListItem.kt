package com.aldeadavila.suggestionbox.ui.screens.admin.product.list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.aldeadavila.suggestionbox.R
import com.aldeadavila.suggestionbox.domain.model.Product
import com.aldeadavila.suggestionbox.ui.navigation.screen.admin.AdminCategoryScreen
import com.aldeadavila.suggestionbox.ui.screens.admin.product.list.AdminProductListViewModel

@Composable
fun AdminProductListItem(
    navHostController: NavHostController,
    product: Product,
    vm: AdminProductListViewModel = hiltViewModel()
) {

    Column(
        modifier = Modifier
            .padding(
                start = 20.dp,
                end = 20.dp,
                top = 15.dp
            )
            .height(90.dp)

    ) {
        Row {
            AsyncImage(
                modifier = Modifier
                    .size(70.dp)
                    .clip(RoundedCornerShape(10.dp)),
                model = product.image1,
                contentDescription = ""
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = product.name,
                    color = Color.Black,
                    fontSize = 17.sp
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = product.description,
                    color = Color.Gray,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = product.price.toString(),
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }
            Spacer(modifier = Modifier.width(15.dp))
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier
                        .size(25.dp)
                        .clickable {
                            navHostController.navigate(
                                route = AdminCategoryScreen.ProductUpdate.passProduct(
                                    product.toJson()
                                )
                            )
                        },
                    painter = painterResource(id = R.drawable.edit),
                    contentDescription = "editar"
                )
                Spacer(modifier = Modifier.height(5.dp))
                Image(
                    modifier = Modifier
                        .size(25.dp)
                        .clickable {
                            vm.deleteProduct(product.id ?: "")
                        },
                    painter = painterResource(id = R.drawable.trash),
                    contentDescription = "eliminar"
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Divider(
            color = Color.LightGray
        )
    }

}