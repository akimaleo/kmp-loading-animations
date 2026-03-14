package com.kawa.loading.kmp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import kotlin.math.ceil

private val INDICATOR_NATIVE_SIZE = 200.dp

private const val CELL_PADDING_FRACTION = 0.1f

private fun optimalGrid(
    itemCount: Int,
    width: Dp,
    height: Dp
): Pair<Int, Int> {
    var bestCols = 1
    var bestRows = itemCount
    var bestCellSize = Dp.Hairline

    for (cols in 1..itemCount) {
        val rows = ceil(itemCount.toFloat() / cols).toInt()
        val cellW = width / cols
        val cellH = height / rows
        val cellSize = min(cellW, cellH)
        if (cellSize > bestCellSize) {
            bestCellSize = cellSize
            bestCols = cols
            bestRows = rows
        }
    }
    return bestCols to bestRows
}

@Preview
@Composable
fun LoadingShowcase() {
    val indicators = indicatorsCatalog

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
    ) {
        val (cols, rows) = optimalGrid(
            itemCount = indicators.size,
            width = maxWidth,
            height = maxHeight
        )

        val cellWidth = maxWidth / cols
        val cellHeight = maxHeight / rows

        // The usable area inside each cell after padding
        val usableCell = min(cellWidth, cellHeight) * (1f - 2 * CELL_PADDING_FRACTION)

        // Scale factor: shrink (or grow) the indicator's native drawing to fit
        val density = LocalDensity.current
        val scaleFactor = with(density) { usableCell.toPx() / INDICATOR_NATIVE_SIZE.toPx() }

        Column(modifier = Modifier.fillMaxSize()) {
            for (row in 0 until rows) {
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    for (col in 0 until cols) {
                        val index = row * cols + col
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .clipToBounds()
                                .padding(
                                    horizontal = cellWidth * CELL_PADDING_FRACTION,
                                    vertical = cellHeight * CELL_PADDING_FRACTION
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            if (index < indicators.size) {
                                Box(
                                    modifier = Modifier.graphicsLayer {
                                        scaleX = scaleFactor
                                        scaleY = scaleFactor
                                    },
                                    contentAlignment = Alignment.Center
                                ) {
                                    indicators[index]()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

