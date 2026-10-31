package io.github.niczzxo.aello.ui.screens.settings.index

import io.github.niczzxo.aello.R
import io.github.niczzxo.aello.ui.components.settings.SettingEntry
import io.github.niczzxo.aello.ui.components.settings.SettingsDestination
import io.github.niczzxo.aello.widget.core.FlowWidgetEntry
import io.github.niczzxo.aello.widget.core.FlowWidgets

internal object WidgetsIndex {
    fun entryFor(widget: FlowWidgetEntry) =
        SettingEntry(
            key = "widgets.${widget.id}",
            title = widget.label,
            summary = widget.description,
            section = R.string.settings_widgets_add_header,
            keywords = R.string.settings_keywords_widgets,
            destination = SettingsDestination.WIDGETS,
        )

    val all = FlowWidgets.catalog.map(::entryFor)
}
