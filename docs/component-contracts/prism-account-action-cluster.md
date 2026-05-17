# Prism Account Action Cluster

`SignalPrismAccountActionCluster` is the selected-account action surface directly under the account hero.

Required actions for Nova Retail:
- Transfer
- Pay bill
- QR
- Statement

The model supports `id`, `deeplink`, and `suggested` so backend capability resolution can reorder or emphasize actions without local styling hacks.

Visual rules:
- Use a translucent Prism surface with soft internal separation.
- One action may be softly emphasized when contextually suggested.
- Avoid four boxed utility tiles and avoid gradients on every action.
- Icons must stay readable at 19-22dp.
